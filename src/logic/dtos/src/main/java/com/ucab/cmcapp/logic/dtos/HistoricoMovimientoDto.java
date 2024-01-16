package com.ucab.cmcapp.logic.dtos;

import java.util.Date;

public class HistoricoMovimientoDto {

    private long id;

    private boolean movimiento;

    private Date fecha;

    private UserDto userDto;

    public HistoricoMovimientoDto() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMovimiento() {
        return movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
