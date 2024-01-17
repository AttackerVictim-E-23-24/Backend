package com.ucab.cmcapp.common.exceptions;

public class FirebaseException extends BaseException{

    public FirebaseException(String user) {
        super("No se ha podido enviar la notifiacion al usuario: " + user);
    }
}
