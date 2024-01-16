package com.ucab.cmcapp.common.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alerta")
public class Alerta {

    public Alerta() {

    }
    public Alerta (Alerta alerta) {
        tipo = alerta.getTipo();
        nivelRiesgo = alerta.getNivelRiesgo();
        _createAt = alerta.get_createAt();
        monitoreo = alerta.getMonitoreo();
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "tipo", nullable = false )
    private String tipo;

    @Column( name = "nivelRiesgo", nullable = false )
    private int nivelRiesgo;

    @Basic(optional = false)
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = true, updatable = false, nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date _createAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "monitoreo_id", nullable = false )
    private MonitoreoElectronico monitoreo;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
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
