package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class UpdateException extends BaseException implements Serializable
{
    public UpdateException( Exception e, String str )
    {
        super( e, str );
    }
}
