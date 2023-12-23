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
        userName = user.userName;
       _createAt = user._createAt;
       _userType = user._userType;
       datosPersona = user.datosPersona;
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
     * Name:                   user_name
     * Description:            User's name
     */
    @Column( name = "user_name", nullable = false )
    private String userName;

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
     * Name:                   imei
     * Description:            User's IMEI
     */
    @Column( name = "user_imei", nullable = false )
    private String _imei;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "persona_id", nullable = false )
    private Persona datosPersona;

    public Persona getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(Persona datosPersona) {
        this.datosPersona = datosPersona;
    }

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

    public String getImei()
    {
        return _imei;
    }

    public void setImei(String imei)
    {
        _imei = imei;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

}
