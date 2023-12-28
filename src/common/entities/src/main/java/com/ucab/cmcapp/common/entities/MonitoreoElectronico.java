package com.ucab.cmcapp.common.entities;


import javax.persistence.*;

@Entity
@Table(name = "monitoreo_electronico")
public class MonitoreoElectronico {
    public MonitoreoElectronico(){

    }

    public MonitoreoElectronico( MonitoreoElectronico monitoreoElectronico )
    {
        frecuencia = monitoreoElectronico.frecuencia;
        tiempoInactividad = monitoreoElectronico.tiempoInactividad;
        tiempoOffline = monitoreoElectronico.tiempoOffline;
        distanciaAlejamiento = monitoreoElectronico.distanciaAlejamiento;
        cedulaAtacante = monitoreoElectronico.cedulaAtacante;
        cedulaVictima = monitoreoElectronico.cedulaVictima;
        atacanteUser = monitoreoElectronico.atacanteUser;
        victimaUser = monitoreoElectronico.victimaUser;
    }

    public MonitoreoElectronico( long id ){
        _id = id;
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "frecuencia", nullable = false )
    private int frecuencia;

    @Column( name = "tiempo_inactividad", nullable = false )
    private int tiempoInactividad;

    @Column( name = "tiempo_offline", nullable = false )
    private int tiempoOffline;

    @Column( name = "distanciaAlejamiento", nullable = false )
    private int distanciaAlejamiento;

    @Column( name = "cedulaAtacante", nullable = false )
    private long cedulaAtacante;

    @Column( name = "cedulaVictima", nullable = false )
    private long cedulaVictima;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "atacante_id", nullable = false )
    private User atacanteUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "victima_id", nullable = false )
    private User victimaUser;

    public long get_id() {
        return _id;
    }

    public void set_id(long id) {
        _id = id;
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

    public User getAtacanteUser() {
        return atacanteUser;
    }

    public void setAtacanteUser(User atacanteUser) {
        this.atacanteUser = atacanteUser;
    }

    public User getVictimaUser() {
        return victimaUser;
    }

    public void setVictimaUser(User victimaUser) {
        this.victimaUser = victimaUser;
    }
}
