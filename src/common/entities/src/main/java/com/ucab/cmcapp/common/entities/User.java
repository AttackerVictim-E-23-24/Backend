package com.ucab.cmcapp.common.entities;


import javax.persistence.*;
import java.util.Date;

/**
 * Name:                User.
 * Description:         Entity User.
 */
@Entity
@Table(name = "user")
public class User
{/**
     * Name:                User
     * Description:         Method to initialize a User (Empty constructor)
     */
    public User()
    {
    }


    /**
     * Name:                User
     * Description:         Method to initialize a User with userDto
     */
    public User( User user )
    {
       _email = user._email;
       _createAt = user._createAt;
       _userType = user._userType;
       _termCondition = user._termCondition;
    }

    /**
     * Name:                User
     * Description:         Method to initialize a User
     */
    public User( long id )
    {
        _id = id;
    }


    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    /**
     * Name:                   email
     * Description:            User's Email
     */
    @Column( name = "email", nullable = false )
    private String _email;

    /**
     * Name:                   password
     * Description:            User's Password
     */
    @Transient
    private String _password;

    /**
     * Name:                   uid
     * Description:            User's Uid
     */
    @Column( name = "uid", nullable = false )
    private String _uid;

    /**
     * Name:                   term_condition
     * Description:            Indicates if the user has accepted the terms and conditions
     */
    @Basic(optional = false)
    @Column( name = "term_condition", insertable = false, columnDefinition="BOOLEAN DEFAULT FALSE" )
    private Boolean _termCondition;


    /**
     * Name:                   Created Day At
     * Description:            User's Creation Day Attribute
     */
    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false, nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date _createAt;


    /**
     * Name:                   UserType
     * Description:            User's UserType Entity
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "user_type_id", nullable = false )
    private UserType _userType;



    public long getId()
    {
        return _id;
    }

    public void setId( long id )
    {
        _id = id;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(String email)
    {
        _email = email;
    }

    public String getPassword()
    {
        return _password;
    }

    public void setPassword( String password )
    {
        _password = password;
    }

    public String getUid()
    {
        return _uid;
    }

    public void setUid( String uid )
    {
        _uid = uid;
    }

    public Boolean getTermCondition()
    {
        return _termCondition;
    }

    public void setTermCondition( Boolean termCondition )
    {
        _termCondition = termCondition;
    }

    public Date getCreateAt()
    {
        return _createAt;
    }

    public void setCreateAt(Date createAt)
    {
        _createAt = createAt;
    }

    public UserType getUserType()
    {
        return _userType;
    }

    public void setUserType(UserType userType)
    {
        _userType = userType;
    }

}
