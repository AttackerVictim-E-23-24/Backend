package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class CupraException extends BaseException implements Serializable
{
    public CupraException(String ex)
    {
        super( ex );
    }

}
