package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class InsertException extends BaseException implements Serializable
{
    public InsertException( String e )
    {
        super( e );
    }
}
