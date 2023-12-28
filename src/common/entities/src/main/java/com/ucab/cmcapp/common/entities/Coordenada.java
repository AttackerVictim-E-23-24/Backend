package com.ucab.cmcapp.common.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coordenada")
public class Coordenada {
    public Coordenada() {

    }

    public Coordenada (Coordenada coordenada) {
        latitud = coordenada.latitud;
        longitud = coordenada.longitud;
        _createAt = coordenada._createAt;
    }

    public Coordenada (double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Coordenada (long id) {
        _id = id;
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "latitud", nullable = false )
    private double latitud;

    @Column( name = "longitud", nullable = false )
    private double longitud;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false, nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date _createAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "zona_seguridad_id", nullable = true )
    private UserType zonaSegId;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Date get_createAt() {
        return _createAt;
    }

    public void set_createAt(Date _createAt) {
        this._createAt = _createAt;
    }
}
