package com.ucab.cmcapp.logic.dtos;

import java.util.List;

public class UserDto
{
    private Long id;
    private String userName;
    private String imei;

    private String password;

    private UserTypeDto userType;

    private String email;

    private PersonaDto datosPersona;

    public UserDto()
    {
    }
    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public UserTypeDto getUserTypeDto()
    {
        return userType;
    }

    public void setUserTypeDto( UserTypeDto userType )
    {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public PersonaDto getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(PersonaDto datosPersona) {
        this.datosPersona = datosPersona;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String _imei) {
        this.imei = _imei;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }
}
