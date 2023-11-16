package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class UserMapper extends BaseMapper
{
    private static Logger _logger = LoggerFactory.getLogger( UserMapper.class );

    public static User mapDtoToEntity( UserDto dto ) throws ParseException
    {
        User entity = EntityFactory.createUser();

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        entity.setEmail( dto.getEmail() );
        entity.setTermCondition(dto.getTermCondition());
        entity.setUid( dto.getUid() );

        if ( Objects.nonNull( dto.getUserTypeDto() ) )
        {
            entity.setUserType( UserTypeMapper.mapDtoToEntity( dto.getUserTypeDto() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static UserDto mapEntityToDto( User entity )
    {
        final UserDto dto = new UserDto();

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapEntityToDto: entity {}", entity );
        //endregion

        dto.setId( entity.getId());
        dto.setEmail(  entity.getEmail() );
        dto.setUid( entity.getUid() );
        if(Objects.nonNull(entity.getUserType()))
            dto.setUserTypeDto( UserTypeMapper.mapEntityToDto( entity.getUserType() ));

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }

    public static User mapDtoToEntity( long id )
    {
        User entity = EntityFactory.createUser( id );

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntity: id {}", id );
        //endregion

        entity.setId( id );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static User mapDtoToEntityEmail( String email )
    {
        User entity = EntityFactory.createUser();

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntityEmail: email {}", email );
        //endregion

        entity.setEmail( email );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntityEmail: entity {}", entity );
        //endregion

        return entity;
    }

}
