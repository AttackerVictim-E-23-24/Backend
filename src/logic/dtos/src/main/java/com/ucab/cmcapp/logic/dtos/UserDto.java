package com.ucab.cmcapp.logic.dtos;


import java.util.List;

public class UserDto extends BaseDto
{
    private String _uid;
    private String _email;
    private String _createAt;
    private Boolean _termCondition;
    private UserTypeDto _userType;


    public UserDto()
    {
    }

    public UserDto( long id )
    {
        super( id );
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail( String email )
    {
        _email = email;
    }

    public String getUid()
    {
        return _uid;
    }

    public void setUid( String uid )
    {
        _uid = uid;
    }

    public String getCreateAt()
    {
        return _createAt;
    }

    public void setCreateAt( String createAt )
    {
        _createAt = createAt;
    }

    public Boolean getTermCondition() {
        return _termCondition;
    }

    public void setTermCondition(Boolean termCondition) {
        _termCondition = termCondition;
    }

    public UserTypeDto getUserTypeDto()
    {
        return _userType;
    }

    public void setUserTypeDto( UserTypeDto userType )
    {
        _userType = userType;
    }

}
