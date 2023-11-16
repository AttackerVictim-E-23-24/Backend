package com.ucab.cmcapp.common.exceptions;

public class FaultBean
{
    private String _code;
    private String _message;
    private String _sopportmessage;

    public FaultBean( String code, String message, String soportMessage )
    {
        _code = code;
        _message = message;
        _sopportmessage = soportMessage ;
    }

    public String getCode()
    {
        return _code;
    }

    public void setCode( String code )
    {
        _code = code;
    }

    public String getSoportMessage()
    {
        return _sopportmessage;
    }

    public void setSoportMessage( String sopportmessage )
    {
        _sopportmessage = sopportmessage;
    }


    public String getMessage()
    {
        return _message;
    }

    public void setMessage( String message )
    {
        this._message = message;
    }
}
