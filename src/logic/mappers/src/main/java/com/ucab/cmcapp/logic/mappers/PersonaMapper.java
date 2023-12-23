package com.ucab.cmcapp.logic.mappers;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.dtos.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PersonaMapper extends BaseMapper{

    private static Logger _logger = LoggerFactory.getLogger( PersonaMapper.class );

    public static Persona mapDtoToEntity( PersonaDto dto ){

        Persona entity = EntityFactory.createPersona();

        //region Instrumentation DEBUG
        _logger.debug( "Get in PersonaMapper.mapDtoToEntity: dto {}", dto );
        //endregion

        entity.setNombre( dto.getNombre() );
        entity.setSeg_nombre( dto.getSeg_nombre() );
        entity.setApellido( dto.getApellido() );
        entity.setSeg_apellido( dto.getSeg_apellido() );
        entity.setCedula( dto.getCedula() );
        entity.setFch_nacimiento( dto.getFch_nac() );
        entity.setDireccion( dto.getDireccion() );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving PersonaMapper.mapDtoToEntity: entity {}", entity );
        //endregion

        return entity;
    }

    public static PersonaDto mapEntityToDto( Persona entity ) {

        PersonaDto dto = new PersonaDto();

        //region Instrumentation DEBUG
        _logger.debug("Get in PersonaMapper.mapEntityToDto: entity {}", entity);
        //endregion

        dto.setCedula(entity.getCedula());
        dto.setFch_nac(entity.getFch_nacimiento());
        dto.setSeg_apellido(entity.getSeg_apellido());
        dto.setSeg_nombre(entity.getSeg_nombre());
        dto.setApellido(entity.getApellido());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving PersonaMapper.mapEntityToDto: dto {}", dto );
        //endregion
        return dto;
    }


}
