package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.*;


import com.ucab.cmcapp.common.util.GmailCodes;
import com.ucab.cmcapp.common.util.LDAP;
import com.ucab.cmcapp.common.util.ServiceResponse;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.GetCoordenadaByUsernameIdCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.CreateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.GetMailCodeByCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.composite.CreateMailCodeCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByUsernameCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;

import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.dtos.MailCodeDto;
import com.ucab.cmcapp.logic.dtos.UserDto;

import com.ucab.cmcapp.logic.mappers.CoordenadaMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Path( "/getAll" )
    public ServiceResponse getAllUsers() {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de usuarios");

        List<UserDto> response;

        GetAllUsersCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getAllUsers" );
        //endregion

        try
        {
            command = CommandFactory.createGetAllUsersCommand();
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de usuarios");

            _logger.info( "Response getAllUsers: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all users {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllUsers" );

        return serviceResponse;
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

    @GET
    @Path( "username/{username}" )
    public UserDto getUserByUsername(@PathParam( "username" ) String username )
    {
        User entity;
        UserDto response;
        GetUserByUsernameCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getUser" );
        //endregion

        try
        {
            entity = UserMapper.mapDtoToEntityUsername( username );
            command = CommandFactory.createGetUserByUsernameCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );
            _logger.info( "Response getUser: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting user {}: {}", e.getMessage(), username, e.getCause());
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
    public ServiceResponse addUser(UserDto userDto)
    {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(null);
        serviceResponse.setMensaje("No se ha podido registrar el usuario");

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

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se ha registrado el correctamente");
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
        return serviceResponse;
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


    @POST
    @Path( "/setGeolocationUser" )
    public ServiceResponse addLocalizacion(ArrayList<CoordenadaDto> listDeCoordenadas)
    {

        ServiceResponse serviceResponse = new ServiceResponse();

        ArrayList<CoordenadaDto> coordenadasAgregadas = new ArrayList<CoordenadaDto>();

        Coordenada entity;
        CoordenadaDto response;
        CreateCoordenadaCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.addLozalizacion - Zona de Seguridad o Usuario" );
        //endregion

        //Recorro el Array de Coordenadas y las registro en el determinado usuario

        for (CoordenadaDto coordenada : listDeCoordenadas){

                //La coordenada corresponde a un usuario

                try
                {
                        _logger.debug( "Get in UserService.addLozalizacion - Coordenada de Usuario" );
                        UserDto user = getUserByUsername( coordenada.getUsuarioDto().getUserName() );
                        coordenada.setUsuarioDto( user );
                        entity = CoordenadaMapper.mapDtoToEntity( coordenada );
                        command = CommandFactory.createCreateCoordenadaCommand( entity );
                        command.execute();
                        response = CoordenadaMapper.mapEntityToDto( command.getReturnParam() );

                        coordenadasAgregadas.add( response );

                        serviceResponse.setRespuesta( coordenadasAgregadas );
                        serviceResponse.setStatus(true); //Todo correcto
                        serviceResponse.setMensaje("Se ha registrado la ubicacion del usuario");

                    _logger.info( "Response addCoordenada Usuario: {} ", response );

                }
                catch ( Exception e )
                {
                    _logger.error("error {} adding coordenada usuario: {}", e.getMessage(), e.getCause());
                    serviceResponse.setStatus(false);
                    serviceResponse.setMensaje("Error al registrar la ubicacion del usuario");
                    throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                            entity( e ).build() );
                }
                finally
                {
                    if (command != null)
                        command.closeHandlerSession();
                }

        }

        _logger.debug( "Leaving UserService.addLozalizacion - Zona de Seguridad o Usuario" );
        return serviceResponse;
    }

    @GET
    @Path( "getGeolocation/{nombreUsuario}" )
    public ServiceResponse getGoeolocationOfUser(@PathParam( "nombreUsuario" ) String nombreUsuario )
    {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido recuperar la ubicacion del usuario");

        CoordenadaDto coordenadaDto = new CoordenadaDto();
        Coordenada entity;
        CoordenadaDto response;
        GetCoordenadaByUsernameIdCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getGoeolocationOfUser" );
        //endregion

        try
        {
            // Primero obtengo el usuario al cual esta asociado la coordenada por su nombre de usuario
            UserDto user = getUserByUsername( nombreUsuario );
            coordenadaDto.setUsuarioDto( user );

            //Buscamos aquella coordenada correspondiente

            entity = CoordenadaMapper.mapDtoToEntityByUser( user );
            command = CommandFactory.createGetCoordenadaByUsernameIdCommand( entity );
            command.execute();
            response = CoordenadaMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta( response );
            serviceResponse.setMensaje("Se ha recuperado la ubicacion del usuario");

            _logger.info( "Response getCoordenadaByUsername: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting coordenada by username {}: {}", e.getMessage(), nombreUsuario, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getGoeolocationOfUser" );

        return serviceResponse;
    }

}
