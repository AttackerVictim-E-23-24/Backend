package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class FindAllException extends BaseException implements Serializable
{
    public FindAllException( String str )
    {
        super( str );
    }
}

