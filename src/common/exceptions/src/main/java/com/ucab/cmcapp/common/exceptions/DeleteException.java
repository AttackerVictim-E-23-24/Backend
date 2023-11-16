package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class DeleteException extends BaseException implements Serializable
{
    public DeleteException( String str )
    {
        super( str );
    }
}
