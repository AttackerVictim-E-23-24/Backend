package com.ucab.cmcapp.common.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historico_movimiento")
public class HistoricoMovimiento {

    public HistoricoMovimiento() {

    }
    public HistoricoMovimiento (HistoricoMovimiento historicoMovimiento) {
        movimiento = historicoMovimiento.isMovimiento();
        _fecha = historicoMovimiento._fecha;
        usuario = historicoMovimiento.usuario;
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "movimiento", nullable = false )
    private boolean movimiento;

    @Basic(optional = false)
    @Column(name = "fecha", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = true, updatable = false, nullable = false)
    @Temporal( TemporalType.TIMESTAMP)
    private Date _fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "usuario_id", nullable = false )
    private User usuario;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean moving) {
        movimiento = moving;
    }

    public Date get_fecha() {
        return _fecha;
    }

    public void set_fecha(Date _fecha) {
        this._fecha = _fecha;
    }
}
