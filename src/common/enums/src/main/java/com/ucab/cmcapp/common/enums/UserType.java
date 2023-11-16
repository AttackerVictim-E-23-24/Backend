package com.ucab.cmcapp.common.enums;

public enum UserType
{

    ADMINISTRATOR( 1L ),
    USER( 2L );

    private final Long _value;

    UserType(Long value)
    {
        _value = value;
    }

    public Long getValue()
    {
        return _value;
    }
}
