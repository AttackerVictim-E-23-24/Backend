package com.ucab.cmcapp.logic.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserTypeDto
{
    private long id;
    private String name;

    public UserTypeDto(){}

    public UserTypeDto( long id )
    {
        setId( id );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

}
