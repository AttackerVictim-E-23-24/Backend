package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.UserType;
import com.ucab.cmcapp.logic.dtos.UserTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTypeMapper extends BaseMapper
{
    private static Logger _logger = LoggerFactory.getLogger( UserMapper.class );

    public static UserType mapDtoToEntity( UserTypeDto dto )
    {
        UserType entity = EntityFactory.createUserType( dto.getId() );

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        entity.setName( dto.getName() );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static UserTypeDto mapEntityToDto( UserType entity )
    {
        final UserTypeDto dto = new UserTypeDto();

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserMapper.mapEntityToDto: entity {}", entity );
        //endregion

        dto.setId( entity.getId());
        dto.setName( entity.getName());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UserMapper.mapEntityToDto: dto {}", dto );
        //endregion

        return dto;
    }
}
