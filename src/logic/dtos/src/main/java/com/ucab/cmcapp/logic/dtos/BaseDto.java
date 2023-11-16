package com.ucab.cmcapp.logic.dtos;

import com.ucab.cmcapp.common.exceptions.BadIdException;

public abstract class BaseDto
{
    private long _id;


    public BaseDto( long id )
    {
        setId( id );
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
}
