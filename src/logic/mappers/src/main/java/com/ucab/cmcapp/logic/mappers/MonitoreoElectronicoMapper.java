package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import com.ucab.cmcapp.logic.dtos.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Objects;

public class MonitoreoElectronicoMapper extends BaseMapper{

    private static Logger _logger = LoggerFactory.getLogger( MonitoreoElectronicoMapper.class );

    public static MonitoreoElectronico mapDtoToEntity(MonitoreoElectronicoDto dto ) throws ParseException {

        MonitoreoElectronico entity = EntityFactory.createMonitoreoElectronico();

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        if( Objects.nonNull( dto.getId() ) )
        {
            entity.set_id( dto.getId() );
        }

        entity.setFrecuencia( dto.getFrecuencia() );

        entity.setTiempoInactividad( dto.getTiempoInactividad() );

        entity.setDistanciaAlejamiento( dto.getDistanciaAlejamiento() );

        entity.setTiempoOffline( dto.getTiempoOffline() );

        entity.setCedulaAtacante( dto.getCedulaAtacante() );

        entity.setCedulaVictima( dto.getCedulaVictima() );

        if( Objects.nonNull( dto.getAtacanteUser() ) )
        {
            entity.setAtacanteUser( UserMapper.mapDtoToEntity( dto.getAtacanteUser().getId() ) );
        }

        if( Objects.nonNull( dto.getVictimaUser() ) )
        {
            entity.setAtacanteUser( UserMapper.mapDtoToEntity( dto.getVictimaUser().getId() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MonitoreoElectronicoMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static MonitoreoElectronicoDto mapEntityToDto( MonitoreoElectronico entity ) {

        MonitoreoElectronicoDto dto = new MonitoreoElectronicoDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in MonitoreoElectronicoMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setId( entity.get_id() );
        dto.setFrecuencia( entity.getFrecuencia() );
        dto.setDistanciaAlejamiento( entity.getDistanciaAlejamiento() );
        dto.setTiempoInactividad( entity.getTiempoInactividad() );
        dto.setTiempoOffline( entity.getTiempoOffline() );
        dto.setCedulaAtacante( entity.getCedulaAtacante() );
        dto.setCedulaVictima( entity.getCedulaVictima() );
        dto.setAtacanteUser( UserMapper.mapEntityToDto( entity.getAtacanteUser() ) );
        dto.setVictimaUser( UserMapper.mapEntityToDto( entity.getVictimaUser() ) );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MonitoreoElectronicoMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }

    public static MonitoreoElectronico mapDtoToEntityByCedula(long cedulaAtacante, long cedulaVictima ) throws ParseException {

        MonitoreoElectronico entity = EntityFactory.createMonitoreoElectronico();

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoMapper.mapDtoToEntity: cedula atacante y cedula victima {}", cedulaAtacante, cedulaVictima );
        //endregion

        entity.setCedulaAtacante( cedulaAtacante );

        entity.setCedulaVictima( cedulaVictima );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MonitoreoElectronicoMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static MonitoreoElectronico mapDtoToEntityByCedulaNombreUsuario(long cedulaAtacante) throws ParseException {

        MonitoreoElectronico entity = EntityFactory.createMonitoreoElectronico();

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoMapper.mapDtoToEntity: cedula atacante o victima{}", cedulaAtacante);
        //endregion

        entity.setCedulaAtacante( cedulaAtacante );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving MonitoreoElectronicoMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

}
