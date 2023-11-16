package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class AuthenticationException extends RuntimeException implements Serializable
{
    public AuthenticationException( String str )
    {
        super( str );
    }
}
