package com.ucab.cmcapp.logic.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserTypeDto extends BaseDto
{
    private String _name;
    private List<UserDto> _userDtoList;

    public UserTypeDto()
    {
        _userDtoList = new ArrayList<>( );
    }

    public UserTypeDto( long id )
    {
        super( id );
        _userDtoList = new ArrayList<>();
    }

    public String getName()
    {
        return _name;
    }

    public void setName( String name )
    {
        _name = name;
    }

    public List<UserDto> getUserDtoList()
    {
        return _userDtoList;
    }

    public void setUserDtoList( List<UserDto> userDtoList )
    {
        _userDtoList = userDtoList;
    }

}
