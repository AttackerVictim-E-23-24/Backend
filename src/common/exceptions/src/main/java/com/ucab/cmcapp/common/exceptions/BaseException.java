package com.ucab.cmcapp.common.exceptions;

public class BaseException extends RuntimeException
{

    public BaseException( IllegalArgumentException e )
    {
    }

    public BaseException( Exception e, String str )
    {
        super( str, e, false, false );
    }

    public BaseException(String str)
    {
        super(str);
    }

    public BaseException(Exception e)
    {
        super(e);
    }


}
