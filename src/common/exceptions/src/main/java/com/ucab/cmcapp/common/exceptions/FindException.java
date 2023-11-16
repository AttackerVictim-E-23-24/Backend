package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class FindException extends BaseException implements Serializable
{
    public FindException( String str )
    {
        super( str );
    }

}
