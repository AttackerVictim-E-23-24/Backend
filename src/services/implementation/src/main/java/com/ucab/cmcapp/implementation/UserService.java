package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.*;


import com.ucab.cmcapp.common.util.GmailCodes;
import com.ucab.cmcapp.common.util.LDAP;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.GetMailCodeByCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.composite.CreateMailCodeCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;

import com.ucab.cmcapp.logic.dtos.MailCodeDto;
import com.ucab.cmcapp.logic.dtos.UserDto;

import com.ucab.cmcapp.logic.mappers.MailCodeMapper;
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
    @Path( "/setUser" )
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDto addUser(UserDto userDto)
    {
        User entity;
        UserDto response;
        CreateUserCommand command = null;

        // Se crea una un objeto de personaService para registar al objeto independiente primero que el dependiente (usuario)
        long idPersona = PersonaService.addPersona(userDto);

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.addUser" );
        //endregion

        try
        {
            entity = UserMapper.mapDtoToEntity( userDto );
            Persona nuevaPersona = entity.getDatosPersona();
            nuevaPersona.set_id(idPersona);
            entity.setDatosPersona(nuevaPersona);
            command = CommandFactory.createCreateUserCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response addUser: {} ", response );

            LDAP ldap = new LDAP();
            ldap.newConnection();
            ldap.addUser(userDto.getDatosPersona().getNombre(), userDto.getUserName(), userDto.getPassword());
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

    @GET
    @Path( "/authUser/{usuario}/{password}" )
    public ServiceResponse autenticarUsuario(@PathParam( "usuario" ) String usuario, @PathParam( "password" ) String password){

        ServiceResponse respuesta= new ServiceResponse();

        boolean respuestaLdap = LDAP.authUser(usuario, password);

        if(respuestaLdap){
            respuesta.setMensaje("Autenticacion realizada correctamente");
            respuesta.setStatus(true);
        } else {
            respuesta.setMensaje("Autenticacion fallida");
        }

        respuesta.setRespuesta(respuestaLdap);

        return respuesta;

    }

    @GET
    @Path( "/recPassword/{correo}/" )
    public ServiceResponse enviarCodigoCorreo(@PathParam( "correo" ) String correo){

        correo = correo + "@gmail.com";

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.enviarCodigoCorreo: "+correo);
        //endregion

        ServiceResponse respuesta= new ServiceResponse();

        GmailCodes gmail = new GmailCodes();
        gmail.createEmail(correo);

        int respuestaGmail = gmail.sendEmail();

        MailCodes entity;
        MailCodeDto response;
        CreateMailCodeCommand command = null;

        MailCodeDto mailCodeDto = new MailCodeDto(respuestaGmail);

        try
        {
            // Se busca el usuario al cual pertenece el codigo para poder registrar el codigo con su llave foranea

            UserDto user = getUser( correo );

            mailCodeDto.setUser( user ); //línea necesaria

            //Se mapea de dto user a la entity
            User userEntity = UserMapper.mapDtoToEntity( user );

            //Se mapea de dto mailCodeDto a la entity
            entity = MailCodeMapper.mapDtoToEntity( mailCodeDto );

            //A la entidad es a la cual se le asigna el valor de las llaves foraneas (objetos)
            entity.setUser( userEntity );

            command = CommandFactory.createCreateMailCodeCommand( entity );
            command.execute();
            response = MailCodeMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response addMailCode: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} adding mailCode: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        if(respuestaGmail != 0){
            respuesta.setMensaje("Se ha enviado el código solicitado al respectivo correo electrónico");
            respuesta.setStatus(true);
            respuesta.setRespuesta(true);
        } else {
            respuesta.setMensaje("NO se ha podido enviar el código solicitado al respectivo correo electrónico");
            respuesta.setStatus(false);
        }

        _logger.debug( "Leaving UserService.enviarCodigoCorreo" );

        return respuesta;

    }

    @GET
    @Path( "/recPassword/{correo}/{codigo}/{password}/" )
    public ServiceResponse recuperarPassword(@PathParam( "correo" ) String correo, @PathParam( "codigo" ) int codigo, @PathParam( "password" ) String password){

        ServiceResponse respuesta= new ServiceResponse();
        respuesta.setStatus(true);

        MailCodes entity;
        MailCodeDto response;
        GetMailCodeByCodeCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.recuperarPassword" );
        //endregion

        try
        {
            entity = MailCodeMapper.mapDtoToEntity( codigo );
            command = CommandFactory.createGetMailCodeByCodeCommand( entity );
            command.execute();
            response = MailCodeMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response getMailCode: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting mailCode {}: {}", e.getMessage(), codigo, e.getCause());
            respuesta.setStatus(false);
            respuesta.setRespuesta(false);
            respuesta.setMensaje("El codigo no coincide con el que fue enviado al respectivo correo electrónico");
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        if(respuesta.isStatus()){

            UserDto user = getUser( correo );
            String usuario = user.getUserName();

            LDAP ldap = new LDAP();
            ldap.newConnection();
            ldap.updateUserPassword( usuario, password );

        }

        _logger.debug( "Leaving UserService.recuperarPassword" );

        return respuesta;

    }


}
