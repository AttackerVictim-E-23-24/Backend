package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class JWTCreateException extends BaseException implements Serializable
{
    public JWTCreateException( String e )
    {
        super( e );
    }
}
