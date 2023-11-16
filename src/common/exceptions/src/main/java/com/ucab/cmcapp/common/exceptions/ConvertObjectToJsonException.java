package com.ucab.cmcapp.common.exceptions;

import java.io.Serializable;

public class ConvertObjectToJsonException extends BaseException implements Serializable
{

    public ConvertObjectToJsonException(String str)
    {
        super(str);
    }

}
