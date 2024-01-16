package com.ucab.cmcapp.common.exceptions;

public class PosicionamientoException extends BaseException{

    public PosicionamientoException(String message) {
        super("No se ha podido hacer las validaciones de posicionamiento para el usuario: " + message);
    }
}
