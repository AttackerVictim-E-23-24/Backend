package com.ucab.cmcapp.common.util;

public class MailCodesGenerator {

    static final int longitudCodigo = 6;

    public static String generarStringNumerosAleatorios() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitudCodigo; i++) {
            sb.append((char) (Math.random() * 10 + '0'));
        }
        return sb.toString();
    }

}
