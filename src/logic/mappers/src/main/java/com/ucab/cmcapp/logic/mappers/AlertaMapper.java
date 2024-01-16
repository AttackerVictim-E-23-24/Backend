package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Alerta;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.dtos.AlertaDto;
import com.ucab.cmcapp.logic.dtos.HistoricoMovimientoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class AlertaMapper extends BaseMapper{
    private static Logger _logger = LoggerFactory.getLogger( AlertaMapper.class );

    public static Alerta mapDtoToEntity(AlertaDto dto ) throws ParseException {

        Alerta entity = EntityFactory.createAlerta();

        //region Instrumentation DEBUG
        _logger.debug( "Get in AlertaMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.set_id( dto.getId() );
        }

        entity.setTipo( dto.getTipo() );
        entity.setNivelRiesgo( dto.getNivelRiesgo() );
        entity.set_createAt( dto.getCreatedAt() );

        if( Objects.nonNull( dto.getMonitoreoDto() ) ) {
            entity.setMonitoreo( MonitoreoElectronicoMapper.mapDtoToEntity( dto.getMonitoreoDto() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving AlertaMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static AlertaDto mapEntityToDto( Alerta entity ) {

        AlertaDto dto = new AlertaDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in AlertaMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId(entity.get_id());
        dto.setTipo(entity.getTipo());
        dto.setNivelRiesgo(entity.getNivelRiesgo());
        dto.setCreatedAt(entity.get_createAt());

        if( Objects.nonNull( entity.getMonitoreo()) ) {
            dto.setMonitoreoDto( MonitoreoElectronicoMapper.mapEntityToDto( entity.getMonitoreo() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving AlertaMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }
}
