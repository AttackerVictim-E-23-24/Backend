package com.ucab.cmcapp.common.exceptions;


import java.io.Serializable;

public class JWTVerifyException extends RuntimeException implements Serializable
{

    public JWTVerifyException(String ex)
    {
        super( ex );
    }

}
