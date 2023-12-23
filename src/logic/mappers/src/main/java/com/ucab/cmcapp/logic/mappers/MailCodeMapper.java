package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.MailCodes;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.dtos.MailCodeDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class MailCodeMapper {

    private static Logger _logger = LoggerFactory.getLogger( MailCodeMapper.class );

    public static MailCodes mapDtoToEntity(MailCodeDto dto ) throws ParseException
    {
        MailCodes entity = EntityFactory.createMailCodes();

        //region Instrumentation DEBUG
        _logger.debug( "Get in MailCodeMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        entity.setCode( dto.getCode() );

        entity.setUser( UserMapper.mapDtoToEntity( dto.getUser() ) );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MailCodeMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static MailCodeDto mapEntityToDto( MailCodes entity )
    {
        final MailCodeDto dto = new MailCodeDto();

        //region Instrumentation DEBUG
        _logger.debug( "Get in MailCodeMapper.mapEntityToDto: entity {}", entity );
        //endregion

        dto.setCode( entity.getCode() );

        dto.setUser( UserMapper.mapEntityToDto( entity.getUser() ) );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MailCodeMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }

    public static MailCodes mapDtoToEntity( int code )
    {
        MailCodes entity = EntityFactory.createMailCodes( code );

        //region Instrumentation DEBUG
        _logger.debug( "Get in MailCodeMapper.mapDtoToEntity: id {}", code );
        //endregion

        entity.setCode( code );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MailCodeMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

}
