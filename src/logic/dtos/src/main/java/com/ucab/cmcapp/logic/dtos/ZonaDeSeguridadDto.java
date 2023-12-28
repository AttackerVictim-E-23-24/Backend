package com.ucab.cmcapp.logic.dtos;

import java.util.Date;

public class ZonaDeSeguridadDto {

    private long id;

    private boolean activo;

    private Date createdAt;

    private MonitoreoElectronicoDto monitoreo;

    public ZonaDeSeguridadDto(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MonitoreoElectronicoDto getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(MonitoreoElectronicoDto monitoreo) {
        this.monitoreo = monitoreo;
    }
}
