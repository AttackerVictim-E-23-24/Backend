package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class CoordenadaMapper extends BaseMapper{

    private static Logger _logger = LoggerFactory.getLogger( CoordenadaMapper.class );

    public static Coordenada mapDtoToEntity(CoordenadaDto dto ) throws ParseException {

        Coordenada entity = EntityFactory.createCoordenada();

        //region Instrumentation DEBUG
        _logger.debug( "Get in CoordenadaMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.set_id( dto.getId() );
        }

        entity.setLatitud( dto.getLatitud() );
        entity.setLongitud( dto.getLongitud() );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CoordenadaMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static CoordenadaDto mapEntityToDto( Coordenada entity ) {

        CoordenadaDto dto = new CoordenadaDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in CoordenadaMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId( entity.get_id() );
        dto.setLatitud( entity.getLatitud() );
        dto.setLongitud( entity.getLongitud() );
        dto.setCreatedAt( entity.get_createAt() );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CoordenadaMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }
}
