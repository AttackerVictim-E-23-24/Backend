package com.ucab.cmcapp.logic.dtos;

import java.util.Date;

public class AlertaDto {

    private long id;

    private String tipo;

    private int nivelRiesgo;

    private Date createdAt;

    private MonitoreoElectronicoDto monitoreoDto;

    public AlertaDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(int nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date fecha) {
        this.createdAt = fecha;
    }

    public MonitoreoElectronicoDto getMonitoreoDto() {
        return monitoreoDto;
    }

    public void setMonitoreoDto(MonitoreoElectronicoDto monitoreoDto) {
        this.monitoreoDto = monitoreoDto;
    }
}
