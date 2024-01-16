package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.dtos.HistoricoMovimientoDto;
import com.ucab.cmcapp.logic.dtos.PersonaDto;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoricoMovimientoMapper extends BaseMapper{

    private static Logger _logger = LoggerFactory.getLogger( HistoricoMovimientoMapper.class );

    public static HistoricoMovimiento mapDtoToEntity(HistoricoMovimientoDto dto ) throws ParseException {

        HistoricoMovimiento entity = EntityFactory.createHistoricoMovimiento();

        //region Instrumentation DEBUG
        _logger.debug( "Get in HistoricoMovimientoMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.set_id( dto.getId() );
        }

        entity.setMovimiento( dto.isMovimiento() );
        entity.set_fecha( dto.getFecha() );

        if( Objects.nonNull( dto.getUserDto() ) ) {
            entity.setUsuario( UserMapper.mapDtoToEntity( dto.getUserDto() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving HistoricoMovimientoMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static HistoricoMovimientoDto mapEntityToDto( HistoricoMovimiento entity ) {

        HistoricoMovimientoDto dto = new HistoricoMovimientoDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in HistoricoMovimientoMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId(entity.get_id());
        dto.setMovimiento(entity.isMovimiento());
        dto.setFecha(entity.get_fecha());

        if( Objects.nonNull( entity.getUsuario() ) ) {
            dto.setUserDto( UserMapper.mapEntityToDto( entity.getUsuario() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving HistoricoMovimientoMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }

    public static List<HistoricoMovimientoDto> mapEntityToDto(List<HistoricoMovimiento> entities) {

        final List<HistoricoMovimientoDto> dtos = new ArrayList<>();

        //region Instrumentation DEBUG
        _logger.debug("Get in HistoricoMovimientoMapper.mapEntityToDto: entities {}", entities);
        //endregion

        for (HistoricoMovimiento entity : entities) {
            HistoricoMovimientoDto dto = new HistoricoMovimientoDto();
            dto.setId(entity.get_id());
            dto.setMovimiento(entity.isMovimiento());
            dto.setFecha(entity.get_fecha());
            dtos.add(dto);
        }

        //region Instrumentation DEBUG
        _logger.debug("Leaving HistoricoMovimientoMapper.mapEntityToDto: dtos {}", dtos);
        //endregion

        return dtos;
    }

}
