package com.ucab.cmcapp.common.entities;

public class UsuarioLdap {

    private String nombreSn;

    private String usuarioCn;

    private String userPassword;

    public UsuarioLdap() {
    }
    public UsuarioLdap(String nombreSn, String usuarioCn, String userPassword) {
        this.nombreSn = nombreSn;
        this.usuarioCn = usuarioCn;
        this.userPassword = userPassword;
    }
    public UsuarioLdap(String usuarioCn, String userPassword) {
        this.usuarioCn = usuarioCn;
        this.userPassword = userPassword;
    }

    public String getNombreSn() {
        return nombreSn;
    }

    public void setNombreSn(String nombreSn) {
        this.nombreSn = nombreSn;
    }

    public String getUsuarioCn() {
        return usuarioCn;
    }

    public void setUsuarioCn(String usuarioCn) {
        this.usuarioCn = usuarioCn;
    }

    public String getUserPassword() {
        return userPassword;
    }


}
