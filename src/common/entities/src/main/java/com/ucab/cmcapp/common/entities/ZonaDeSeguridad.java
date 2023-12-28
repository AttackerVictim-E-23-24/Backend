package com.ucab.cmcapp.common.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zona_de_seguridad")
public class ZonaDeSeguridad {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column(name = "activo", columnDefinition = "boolean")
    private boolean activo;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false, nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date _createAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "monitoreo_id", nullable = false )
    private MonitoreoElectronico monitoreo;

    public ZonaDeSeguridad() {

    }

    public ZonaDeSeguridad(long _id) {
        this._id = _id;
    }

    public ZonaDeSeguridad (boolean activo){
        this.activo = activo;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date get_createAt() {
        return _createAt;
    }

    public void set_createAt(Date _createAt) {
        this._createAt = _createAt;
    }

    public MonitoreoElectronico getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(MonitoreoElectronico monitoreo) {
        this.monitoreo = monitoreo;
    }
}
