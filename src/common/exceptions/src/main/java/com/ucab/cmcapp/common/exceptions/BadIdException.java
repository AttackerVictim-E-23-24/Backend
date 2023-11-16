package com.ucab.cmcapp.common.exceptions;
import java.io.Serializable;

public class BadIdException extends BaseException implements Serializable
{
    public BadIdException( String str )
    {
        super( "Invalid Id: " + str );
    }
}
