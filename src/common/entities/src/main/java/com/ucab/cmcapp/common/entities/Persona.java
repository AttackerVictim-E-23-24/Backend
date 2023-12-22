package com.ucab.cmcapp.common.entities;

import javax.persistence.*;

@Entity
@Table  ( name = "persona" )
public class Persona {

    public Persona(){}

    public Persona( Persona persona ){
        nombre = persona.nombre;
        seg_nombre = persona.seg_nombre;
        apellido = persona.apellido;
        seg_apellido = persona.seg_apellido;
        cedula = persona.cedula;
        fch_nacimiento = persona.fch_nacimiento;
        direccion = persona.direccion;
    }

    public Persona( String nombre, String seg_nombre, String apellido, String seg_apellido, long cedula, String fch_nacimiento, String direccion ){
        this.nombre = nombre;
        this.seg_nombre = seg_nombre;
        this.apellido = apellido;
        this.seg_apellido = seg_apellido;
        this.cedula = cedula;
        this.fch_nacimiento = fch_nacimiento;
        this.direccion = direccion;
    }

    public Persona( long id ){
        this._id = id;
    }

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    @Column( name = "nombre", nullable = false )
    private String nombre;

    @Column( name = "seg_nombre", nullable = false )
    private String seg_nombre;

    @Column( name = "apellido", nullable = false )
    private String apellido;

    @Column( name = "seg_apellido", nullable = false )
    private String seg_apellido;

    @Column( name = "cedula", nullable = false )
    private long cedula;

    @Column( name = "fch_nacimiento", nullable = false )
    private String fch_nacimiento;

    @Column( name = "direccion", nullable = false )
    private String direccion;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeg_nombre() {
        return seg_nombre;
    }

    public void setSeg_nombre(String seg_nombre) {
        this.seg_nombre = seg_nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSeg_apellido() {
        return seg_apellido;
    }

    public void setSeg_apellido(String seg_apellido) {
        this.seg_apellido = seg_apellido;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getFch_nacimiento() {
        return fch_nacimiento;
    }

    public void setFch_nacimiento(String fch_nacimiento) {
        this.fch_nacimiento = fch_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
