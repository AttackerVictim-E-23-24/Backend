package com.ucab.cmcapp.logic.dtos;

public class MonitoreoElectronicoDto {

    private Long id;

    private int frecuencia;

    private int tiempoInactividad;

    private int tiempoOffline;

    private int distanciaAlejamiento;

    private long cedulaAtacante;

    private long cedulaVictima;

    private UserDto atacanteUser;

    private UserDto victimaUser;

    public MonitoreoElectronicoDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getTiempoInactividad() {
        return tiempoInactividad;
    }

    public void setTiempoInactividad(int tiempoInactividad) {
        this.tiempoInactividad = tiempoInactividad;
    }

    public int getTiempoOffline() {
        return tiempoOffline;
    }

    public void setTiempoOffline(int tiempoOffline) {
        this.tiempoOffline = tiempoOffline;
    }

    public int getDistanciaAlejamiento() {
        return distanciaAlejamiento;
    }

    public void setDistanciaAlejamiento(int distanciaAlejamiento) {
        this.distanciaAlejamiento = distanciaAlejamiento;
    }

    public long getCedulaAtacante() {
        return cedulaAtacante;
    }

    public void setCedulaAtacante(long cedulaAtacante) {
        this.cedulaAtacante = cedulaAtacante;
    }

    public long getCedulaVictima() {
        return cedulaVictima;
    }

    public void setCedulaVictima(long cedulaVictima) {
        this.cedulaVictima = cedulaVictima;
    }

    public UserDto getAtacanteUser() {
        return atacanteUser;
    }

    public void setAtacanteUser(UserDto atacanteUser) {
        this.atacanteUser = atacanteUser;
    }

    public UserDto getVictimaUser() {
        return victimaUser;
    }

    public void setVictimaUser(UserDto victimaUser) {
        this.victimaUser = victimaUser;
    }
}
