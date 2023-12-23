package com.ucab.cmcapp.logic.dtos;

import com.ucab.cmcapp.common.exceptions.BadIdException;

public abstract class BaseDto
{
    private long _id;  //Al final no se usa pero se deja como referencia

    public BaseDto( long _id){
        setId( _id );
    }


    public BaseDto()
    {
    }

    public long getId()
    {
        return _id;
    }

    public void setId( long id )
    {
        if ( id >= 0 )
        {
            _id = id;
        }
        else
        {
            throw new BadIdException( Long.toString( _id ) );
        }
    }

    /*
    public void setUserName( String userName )
    {
        if ( userName != null )
        {
            this.userName = userName;
        }
    }



    public String getUserName()
    {
        return userName;
    }

    */

}
