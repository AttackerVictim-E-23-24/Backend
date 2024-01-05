package com.ucab.cmcapp.common.util;

public class ServiceResponse <T> {
    private T respuesta;

    private String mensaje;

    private boolean status;

    public T getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(T respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

