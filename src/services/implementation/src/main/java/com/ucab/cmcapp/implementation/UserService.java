package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/users" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UserService extends BaseService
{
    private static Logger _logger = LoggerFactory.getLogger( UserService.class );

    @GET
    @Path( "/{id}" )
    public UserDto getUser(@PathParam( "id" ) long userId )
    {
        User entity;
        UserDto response;
        GetUserCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getUser" );
        //endregion

        try
        {
            entity = UserMapper.mapDtoToEntity( userId );
            command = CommandFactory.createGetUserCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response getUser: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting user {}: {}", e.getMessage(), userId, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getUser" );
        return response;
    }


    @GET
    @Path( "email/{email}" )
    public UserDto getUser(@PathParam( "email" ) String email )
    {
        User entity;
        UserDto response;
        GetUserByEmailCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getUser" );
        //endregion

        try
        {
            entity = UserMapper.mapDtoToEntityEmail( email );
            command = CommandFactory.createGetUserByEmailCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response getUser: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting user {}: {}", e.getMessage(), email, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getUser" );
        return response;
    }

    @POST
    public UserDto addUser( UserDto userDto )
    {
        User entity;
        UserDto response;
        CreateUserCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.addUser" );
        //endregion

        try
        {
            entity = UserMapper.mapDtoToEntity( userDto );
            command = CommandFactory.createCreateUserCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response addUser: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} adding user: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.addUser" );
        return response;
    }
}
