package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.persona.atomic.GetPersonaByCedulaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.dtos.PersonaDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.mappers.PersonaMapper;

import com.ucab.cmcapp.logic.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class PersonaService extends BaseService{

    private static Logger _logger = LoggerFactory.getLogger( PersonaService.class );


    public static long addPersona (UserDto userDto)
    {
        Persona entity;
        long response;
        CreatePersonaCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in PersonaService.addPersona" );
        //endregion

        try
        {
            entity = PersonaMapper.mapDtoToEntity( userDto.getDatosPersona() );
            command = CommandFactory.createCreatePersonaCommand( entity );
            command.execute();
            response = command.getReturnParam().get_id();
            _logger.info( "Response addPersona: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} adding persona: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.addPersona" );

        return response;
    }

    public static long getPersona(long cedula)
    {
        Persona entity;
        long response;
        GetPersonaByCedulaCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in PersonaService.getPersona" );
        //endregion

        try
        {
            entity = PersonaMapper.mapDtoToEntityCedula( cedula );
            command = CommandFactory.createGetPersonaByCedulaCommand( entity );
            command.execute();
            response = command.getReturnParam().get_id();
            _logger.info( "Response getPersona: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting persona {}: {}", e.getMessage(), cedula, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving PersonaService.getPersona" );
        return response;
    }




}
