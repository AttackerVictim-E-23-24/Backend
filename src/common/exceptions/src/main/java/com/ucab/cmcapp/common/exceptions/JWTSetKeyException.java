package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class JWTSetKeyException extends BaseException implements Serializable
{
    public JWTSetKeyException( String ex )
    {
        super( ex );
    }
}
