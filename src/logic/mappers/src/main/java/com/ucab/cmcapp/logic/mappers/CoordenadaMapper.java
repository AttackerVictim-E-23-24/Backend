package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

        if( Objects.nonNull( dto.getCreatedAt() ) ) {
            entity.set_createAt( dto.getCreatedAt() );
        }

        if( Objects.nonNull( dto.getZonaSegDto() ) ) {
            entity.setZonaSeg( ZonaDeSeguridadMapper.mapDtoToEntity( dto.getZonaSegDto() ) );
        }

        if( Objects.nonNull( dto.getUsuarioDto() ) ) {
            entity.setUsuario( UserMapper.mapDtoToEntity( dto.getUsuarioDto() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CoordenadaMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static Coordenada mapDtoToEntity(double latitud, double longitud, long idZona ) throws ParseException {

        Coordenada entity = EntityFactory.createCoordenada();
        ZonaDeSeguridad zonaSeg = EntityFactory.createZonaDeSeguridad();

        //region Instrumentation DEBUG
        _logger.debug( "Get in CoordenadaMapper.mapDtoToEntity: dto {}",
                latitud,
                longitud );
        //endregion

        entity.setLatitud( latitud );
        entity.setLongitud( longitud );

        zonaSeg.set_id( idZona );
        entity.setZonaSeg( zonaSeg );

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

        if( Objects.nonNull( entity.getZonaSeg() ) ) {
            dto.setZonaSegDto( ZonaDeSeguridadMapper.mapEntityToDto( entity.getZonaSeg() ) );
        }

        if( Objects.nonNull( entity.getUsuario() ) ) {
            dto.setUsuarioDto( UserMapper.mapEntityToDto( entity.getUsuario() ) );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CoordenadaMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }

    public static CoordenadaDto mapEntityToDtoUpdCoord( Coordenada entity ) {

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

    public static Coordenada mapDtoToEntityByUser(UserDto userDto) throws ParseException {

        Coordenada entity = EntityFactory.createCoordenada();

        //region Instrumentation DEBUG
        _logger.debug( "Get in CoordenadaMapper.mapDtoToEntity: dto {}", userDto );
        //endregion

        if( Objects.nonNull( userDto ) )
        {
            entity.setUsuario( UserMapper.mapDtoToEntity( userDto ) );
        }



        //region Instrumentation DEBUG
        _logger.debug( "Leaving CoordenadaMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static List<CoordenadaDto> mapEntityToDto(List<Coordenada> entities) {

        final List<CoordenadaDto> dtos = new ArrayList<>();

        //region Instrumentation DEBUG
        _logger.debug("Get in CoordenadaMapper.mapEntityToDto: entities {}", entities);
        //endregion

        for (Coordenada entity : entities) {
            CoordenadaDto dto = new CoordenadaDto();
            dto.setId(entity.get_id());
            dto.setLatitud(entity.getLatitud());
            dto.setLongitud(entity.getLongitud());
            dto.setCreatedAt(entity.get_createAt());

            if (Objects.nonNull(entity.getZonaSeg())) {
                dto.setZonaSegDto(ZonaDeSeguridadMapper.mapEntityToDto(entity.getZonaSeg()));
            }

            if (Objects.nonNull(entity.getUsuario())) {
                dto.setUsuarioDto(UserMapper.mapEntityToDto(entity.getUsuario()));
            }

            dtos.add(dto);
        }

        //region Instrumentation DEBUG
        _logger.debug("Leaving CoordenadaMapper.mapEntityToDto: dtos {}", dtos);
        //endregion

        return dtos;
    }


}
