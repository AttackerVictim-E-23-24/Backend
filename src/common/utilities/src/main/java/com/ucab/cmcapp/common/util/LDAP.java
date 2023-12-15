package com.ucab.cmcapp.common.util;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
public class LDAP {
    DirContext connection;

    /* create connection during object creation */
    public void newConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");
        try {
            connection = new InitialDirContext(env);
            System.out.println("Conexión Exitosa");
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getAllUsers() throws NamingException {
        String searchFilter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = { "cn", "sn" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("ou=users,ou=system", searchFilter, controls);

        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            String name = attr.get("cn").get(0).toString();
            //deleteUserFromGroup(name,"Administrators");
            System.out.println(attr.get("cn"));
            System.out.println(attr.get("sn"));
        }

    }

    public void addUser(String nombre, String userName, String password) {
        Attributes attributes = new BasicAttributes();
        Attribute attribute = new BasicAttribute("objectClass");
        attribute.add("inetOrgPerson");

        attributes.put(attribute);

        Attribute sn = new BasicAttribute("sn");
        sn.add(nombre);
        attributes.put(sn);

        attributes.put("userPassword", password);


        try {
            connection.createSubcontext("cn="+userName+",ou=users,ou=system", attributes);
            System.out.println("Added user: "+userName);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void deleteUser(String userName)
    {
        try {
            connection.destroySubcontext("cn="+userName+",ou=users,ou=system");
            System.out.println("success, deleted user: "+userName);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void searchUsers() throws NamingException {
        //String searchFilter = "(uid=1)"; //  for one user
        //String searchFilter = "(&(uid=1)(cn=Smith))"; // and condition
        String searchFilter = "(|(uid=1)(uid=2)(cn=Smith))"; // or condition
        String[] reqAtt = { "cn", "sn","uid" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("ou=users,ou=system", searchFilter, controls);

        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            String name = attr.get("cn").get(0).toString();
            //deleteUserFromGroup(name,"Administrators");
            System.out.println(attr.get("cn"));
            System.out.println(attr.get("sn"));
            System.out.println(attr.get("uid"));
        }

    }

    /* use this to authenticate any existing user */
    public static boolean authUser(String username, String password)
    {
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
            env.put(Context.SECURITY_PRINCIPAL, "cn="+username+",ou=users,ou=system");  //check the DN correctly
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);
            System.out.println("success");
            con.close();
            return true;
        }catch (Exception e) {
            System.out.println("failed: "+e.getMessage());
            return false;
        }
    }

    /* use this to update user password */
    public void updateUserPassword(String username, String password) {
        try {
            String dnBase=",ou=users,ou=system";
            ModificationItem[] mods= new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", password));// if you want, then you can delete the old password and after that you can replace with new password
            connection.modifyAttributes("cn="+username +dnBase, mods);//try to form DN dynamically
            System.out.println("success for user: "+username);
        }catch (Exception e) {
            System.out.println("failed: "+e.getMessage());
        }
    }

    public void updateUserDetails(String username, String employeeNumber) {
        try {
            String dnBase=",ou=users,ou=system";
            Attribute attribute = new BasicAttribute("employeeNumber", employeeNumber);
            ModificationItem[] mods= new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
            connection.modifyAttributes("cn="+username +dnBase, mods);//try to form DN dynamically
            System.out.println("success");
        }catch (Exception e) {
            System.out.println("failed: "+e.getMessage());
        }
    }

}
