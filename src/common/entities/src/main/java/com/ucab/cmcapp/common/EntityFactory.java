package com.ucab.cmcapp.common;

import com.ucab.cmcapp.common.entities.*;

public class EntityFactory

{
    /**
     * Method that returns an instance of User class
     */
    public static User createUser()
    {
        return new User();
    }

    /**
     * Method that returns an instance of Persona class
     */
    public static Persona createPersona()
    {
        return new Persona();
    }
    /**
     * Method that returns an instance of User class
     */
    public static User createUser(long id)
    {
        return new User(id);
    }

    /**
     * Method that returns an instance of UserType class
     */
    public static UserType createUserType()
    {
        return new UserType();
    }

    /**
     * Method that returns an instance of UserType class
     */
    public static UserType createUserType(long id)
    {
        return new UserType(id);
    }

    public static UsuarioLdap createUsuarioLdap(){return new UsuarioLdap();}

    public static UsuarioLdap createusuarioLdap(String nombreSn, String usuarioCn, String userPassword){
        return new UsuarioLdap(nombreSn, usuarioCn, userPassword);
    }

    public static UsuarioLdap createusuarioLdap(String usuarioCn, String userPassword){
        return new UsuarioLdap(usuarioCn, userPassword);
    }

    public static MailCodes createMailCodes(){
        return new MailCodes();
    }

    public static MailCodes createMailCodes(int code){
        return new MailCodes(code);
    }

    public static MonitoreoElectronico createMonitoreoElectronico(){
        return new MonitoreoElectronico();
    }

    public static MonitoreoElectronico createMonitoreoElectronico(long id){
        return new MonitoreoElectronico(id);
    }

    public static Coordenada createCoordenada(){
        return new Coordenada();
    }

    public static Coordenada createCoordenada(long id){
        return new Coordenada(id);
    }

    public static Coordenada createCoordenada(double latitud, double longitud){
        return new Coordenada(latitud, longitud);
    }

    public static ZonaDeSeguridad createZonaDeSeguridad(){
        return new ZonaDeSeguridad();
    }

    public static ZonaDeSeguridad createZonaDeSeguridad(long id){
        return new ZonaDeSeguridad(id);
    }

    public static ZonaDeSeguridad createZonaDeSeguridad(boolean activo){
        return new ZonaDeSeguridad(activo);
    }

}