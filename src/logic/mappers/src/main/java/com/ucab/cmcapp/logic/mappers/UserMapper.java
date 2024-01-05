package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.dtos.UserDto;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.dtos.PersonaDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMapper extends BaseMapper
{
    private static Logger _logger = LoggerFactory.getLogger( UserMapper.class );

    public static User mapDtoToEntity( UserDto dto ) throws ParseException
    {
        User entity = EntityFactory.createUser();

        Persona personaEntity = PersonaMapper.mapDtoToEntity( dto.getDatosPersona() );

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.setId( dto.getId() );
        }
        entity.setUserName( dto.getUserName() );
        entity.setImei(dto.getImei());
        entity.setEmail( dto.getEmail() );

        entity.setDatosPersona( personaEntity );

        if ( Objects.nonNull( dto.getUserTypeDto() ) )
        {
            entity.setUserType( UserTypeMapper.mapDtoToEntity( dto.getUserTypeDto() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static UserDto mapEntityToDto( User entity ) {
        final UserDto dto = new UserDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in UserMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId( entity.getId() );
        dto.setUserName(entity.getUserName());
        dto.setImei(entity.getImei());
        dto.setEmail(entity.getEmail());
        dto.setDatosPersona( PersonaMapper.mapEntityToDto( entity.getDatosPersona() ) );
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

    public static User mapDtoToEntityUsername( String username)
    {
        User entity = EntityFactory.createUser();

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntityUsername: username {}", username );
        //endregion

        entity.setUserName( username );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntityUsername: entity {}", entity );
        //endregion

        return entity;
    }

    public static List<UserDto> mapEntityToDto(List<User> entities) {

        final List<UserDto> dtos = new ArrayList<>(); // Create a list to store UserDto objects

        //region Instrumentation DEBUG
        _logger.debug("Get in UserMapper.mapEntityToDto: entities {}", entities);
        //endregion

        for (User entity : entities) {
            UserDto dto = new UserDto();
            dto.setId(entity.getId());
            dto.setUserName(entity.getUserName());
            dto.setImei(entity.getImei());
            dto.setEmail(entity.getEmail());
            dto.setDatosPersona(PersonaMapper.mapEntityToDto(entity.getDatosPersona()));
            if (Objects.nonNull(entity.getUserType())) {
                dto.setUserTypeDto(UserTypeMapper.mapEntityToDto(entity.getUserType()));
            }
            dtos.add(dto); // Add the mapped UserDto object to the list
        }

        //region Instrumentation DEBUG
        _logger.debug("Leaving UserMapper.mapEntityToDto: dtos {}", dtos);
        //endregion

        return dtos;
    }

}
