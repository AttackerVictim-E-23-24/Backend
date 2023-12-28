package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class ZonaDeSeguridadMapper extends BaseMapper {

    private static Logger _logger = LoggerFactory.getLogger( ZonaDeSeguridadMapper.class );

    public static ZonaDeSeguridad mapDtoToEntity(ZonaDeSeguridadDto dto ) throws ParseException {

        ZonaDeSeguridad entity = EntityFactory.createZonaDeSeguridad();

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.set_id( dto.getId() );
        }

        entity.setActivo( dto.isActivo() );

        entity.setMonitoreo( MonitoreoElectronicoMapper.mapDtoToEntity( dto.getMonitoreo() ) );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving ZonaDeSeguridadMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static ZonaDeSeguridadDto mapEntityToDto( ZonaDeSeguridad entity ) {

        ZonaDeSeguridadDto dto = new ZonaDeSeguridadDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in ZonaDeSeguridadMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId( entity.get_id() );
        dto.setCreatedAt( entity.get_createAt() );
        dto.setActivo( entity.isActivo() );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving ZonaDeSeguridadMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }
}
