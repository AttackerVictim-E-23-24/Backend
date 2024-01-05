package com.ucab.cmcapp.logic.dtos;

import javax.xml.registry.infomodel.User;
import java.util.Date;

public class CoordenadaDto {

    private long id;
    private Double latitud;

    private Double longitud;

    private Date createdAt;

    private UserDto usuarioDto;

    private ZonaDeSeguridadDto zonaSegDto;

    public CoordenadaDto(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UserDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public ZonaDeSeguridadDto getZonaSegDto() {
        return zonaSegDto;
    }

    public void setZonaSegDto(ZonaDeSeguridadDto zonaSegDto) {
        this.zonaSegDto = zonaSegDto;
    }
}
