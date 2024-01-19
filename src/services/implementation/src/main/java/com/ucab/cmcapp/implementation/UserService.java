package com.ucab.cmcapp.implementation;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.*;


import com.ucab.cmcapp.common.exceptions.FirebaseException;
import com.ucab.cmcapp.common.exceptions.PosicionamientoException;
import com.ucab.cmcapp.common.util.*;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.GetCoordenadaByUsernameIdCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.CreateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.GetAllCoordenadasByUsernameCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.GetCoordenadasZonaCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.CreateHistoricoCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.GetAllMovimientoBySecondsUsernameCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.GetAllMovimientoByUsernameCommand;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.GetMailCodeByCodeCommand;
import com.ucab.cmcapp.logic.commands.mailcode.composite.CreateMailCodeCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.UpdatePersonaCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByUsernameCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;

import com.ucab.cmcapp.logic.commands.user.composite.UpdateUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.GetAllZonasByUsernameCommand;
import com.ucab.cmcapp.logic.dtos.*;

import com.ucab.cmcapp.logic.mappers.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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

        double latitud;
        double longitud;
        String userName;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.addLozalizacion - Usuario" );
        //endregion

        //Recorro el Array de Coordenadas y las registro en el determinado usuario

        for (CoordenadaDto coordenada : listDeCoordenadas){

                //La coordenada corresponde a un usuario

                try
                {
                        _logger.debug( "Get in UserService.addLozalizacion - Coordenada de Usuario" );
                        latitud = coordenada.getLatitud();
                        longitud = coordenada.getLongitud();
                        userName = coordenada.getUsuarioDto().getUserName();

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

                        validarPosicionamiento(userName, latitud, longitud );

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

        _logger.debug( "Leaving UserService.addLozalizacion - Usuario" );
        return serviceResponse;
    }

    @PUT
    @Path( "/putUser/{username}/" )
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse putUser(  @PathParam( "username" ) String username, PersonaDto personaDto)
    {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(null);
        serviceResponse.setMensaje("No se ha podido actualizar los datos del usuario");

        Persona entity;
        UserDto userDto;
        PersonaDto response;
        UpdatePersonaCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.putUser" );
        //endregion

        try
        {
            userDto = getUserByUsername(username);

            entity = PersonaMapper.mapDtoToEntity( personaDto );
            entity.set_id( userDto.getDatosPersona().get_id() );

            command = CommandFactory.createUpdatePersonaCommand( entity );
            command.execute();
            response = PersonaMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se ha actualizado el usuario correctamente");
            _logger.info( "Response putUser: {} ", response );

        }
        catch ( Exception e )
        {
            _logger.error("error {} updating user: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.putUser" );
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

    @GET
    @Path( "/getGeolocationHistory/{username}" )
    public ServiceResponse getAllCoordenadasByUsername( @PathParam( "username" ) String username ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener el historico de la geolocalizacion del usuario: "+username);

        List<CoordenadaDto> response;

        GetAllCoordenadasByUsernameCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getAllCoordenadasByUsername" );
        //endregion

        try
        {
            UserDto serviceResponseDos = getUserByUsername(username);

            command = CommandFactory.createGetAllCoordenadasByUsernameCommand(serviceResponseDos.getId());
            command.execute();
            response = CoordenadaMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo el historico de la geolocalizacion del usuario");

            _logger.info( "Response getAllCoordenadasByUsername: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting historico de coordenadas {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllCoordenadasByUsername" );

        return serviceResponse;
    }

    @POST
    @Path( "/setMovimiento/{username}" )
    public ServiceResponse addMovimiento(@PathParam( "username" ) String username,  ArrayList<HistoricoMovimientoDto> listDeHistoricoMovimiento)
    {

        ServiceResponse serviceResponse = new ServiceResponse();

        ArrayList<HistoricoMovimientoDto> historicosAgregados = new ArrayList<HistoricoMovimientoDto>();

        HistoricoMovimiento entity;
        HistoricoMovimientoDto response;
        CreateHistoricoCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.addMovimiento: "+username );
        //endregion

        //Recorro el Array de Historico y los registro en el determinado usuario

        for (HistoricoMovimientoDto historico : listDeHistoricoMovimiento){

            //La coordenada corresponde a un usuario

            try
            {
                UserDto user = getUserByUsername( username );
                historico.setUserDto( user );
                entity = HistoricoMovimientoMapper.mapDtoToEntity( historico );
                command = CommandFactory.createCreateHistoricoCommand( entity );
                command.execute();
                response = HistoricoMovimientoMapper.mapEntityToDto( command.getReturnParam() );

                historicosAgregados.add( response );

                serviceResponse.setRespuesta( historicosAgregados );
                serviceResponse.setStatus(true); //Todo correcto
                serviceResponse.setMensaje("Se ha registrado el historico de movimiento del usuario");

                _logger.info( "Response addMovimiento Usuario: {} ", response );

            }
            catch ( Exception e )
            {
                _logger.error("error {} adding movimiento del usuario: {}", e.getMessage(), e.getCause());
                serviceResponse.setStatus(false);
                serviceResponse.setMensaje("Error al registrar el historico de movimiento del usuario");
                throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                        entity( e ).build() );
            }
            finally
            {
                if (command != null)
                    command.closeHandlerSession();
            }

        }

        _logger.debug( "Leaving UserService.addMovimiento" );
        return serviceResponse;
    }

    @GET
    @Path( "/getAllMovimientoByUser/{username}" )
    public ServiceResponse getAllMovimientoByUsername( @PathParam( "username" ) String username ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de historico de movimiento del usuario: "+username);

        List<HistoricoMovimientoDto> response;

        GetAllMovimientoByUsernameCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getAllMovimientoByUsername" );
        //endregion

        try
        {
            UserDto serviceResponseDos = getUserByUsername(username);

            command = CommandFactory.createGetAllMovimientoByUsernameCommand(serviceResponseDos.getId());
            command.execute();
            response = HistoricoMovimientoMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de historicos de movimiento del usuario");

            _logger.info( "Response getAllMovimientoByUsername: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all movimiento {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllMovimientoByUsername" );

        return serviceResponse;
    }

    @GET
    @Path( "getDistance/{nombreUsuarioUno}/{nombreUsuarioDos}" )
    public ServiceResponse getDistance(@PathParam( "nombreUsuarioUno" ) String nombreUsuarioUno, @PathParam( "nombreUsuarioDos" ) String nombreUsuarioDos)
    {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido calcular la distancia entre los usuarios");

        ServiceResponse getGeolocationResponse = new ServiceResponse();

        CoordenadaDto coordenadaDtoUno = new CoordenadaDto();
        CoordenadaDto coordenadaDtoDos = new CoordenadaDto();

        double distance = 0;

        GetCoordenadaByUsernameIdCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getDistance" );
        //endregion

        try
        {

            getGeolocationResponse = getGoeolocationOfUser( nombreUsuarioUno );
            coordenadaDtoUno = (CoordenadaDto) getGeolocationResponse.getRespuesta();

            getGeolocationResponse = getGoeolocationOfUser( nombreUsuarioDos );
            coordenadaDtoDos = (CoordenadaDto) getGeolocationResponse.getRespuesta();

            distance = DistanceCalculator.haversine(coordenadaDtoUno.getLatitud(), coordenadaDtoUno.getLongitud(), coordenadaDtoDos.getLatitud(), coordenadaDtoDos.getLongitud());

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta( distance );
            serviceResponse.setMensaje("Se ha calculado la distancia correctamente");



            _logger.info( "Response getDistance: {} ", distance );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting the distance {}: {}", e.getMessage(), nombreUsuarioUno, nombreUsuarioDos, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getDistance" );

        return serviceResponse;
    }

    @PUT
    @Path( "/putToken/{username}/{token}/" )
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse putUserToken( @PathParam( "username" ) String username, @PathParam( "token" ) String token )
    {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(null);
        serviceResponse.setMensaje("No se ha podido actualizar los datos del usuario");

        User entity;
        UserDto userDto;
        UserDto response;
        UpdateUserCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.putUser" );
        //endregion

        try
        {
            userDto = getUserByUsername(username);

            entity = UserMapper.mapDtoToEntity( userDto );

            entity.setImei( token );
            entity.setTermCondition( true );

            Persona persona = EntityFactory.createPersona();
            persona.set_id( userDto.getDatosPersona().get_id() );

            UserType userType = EntityFactory.createUserType();
            userType.setId( userDto.getUserTypeDto().getId() );

            entity.setDatosPersona( persona );
            entity.setUserType( userType );

            command = CommandFactory.createUpdateUserCommand( entity );
            command.execute();
            response = UserMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se ha actualizado el usuario correctamente");
            _logger.info( "Response putUser: {} ", response );

        }
        catch ( Exception e )
        {
            _logger.error("error {} updating user: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.putUser" );
        return serviceResponse;
    }

    //ENDPOINT que maneja el BOTON SOS

    @GET
    @Path( "/sos/{username}/" )
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse sos(@PathParam( "username" ) String username) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(username);
        serviceResponse.setMensaje("No se ha podido enviar la notificación SOS a las autoridades");

        String tokenAdmin;

        try {
            UserDto adminDto = getUserByUsername("lebron");
            tokenAdmin = adminDto.getImei();

            FirebaseSender.sendMessage("Alerta Maxima - SOS", "El siguiente usuario ha presionado el boton SOS: " + username, tokenAdmin);
            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(username);
            serviceResponse.setMensaje("Se ha enviado el SOS correctamente");
        } catch (FirebaseException e) {
            throw new FirebaseException(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

        return serviceResponse;
    }

    @GET
    @Path( "/puntoDeControl/{username}/" )
    @Consumes(MediaType.APPLICATION_JSON)
    public ServiceResponse puntoDeControl(@PathParam( "username" ) String username) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(username);
        serviceResponse.setMensaje("No se ha podido enviar la alerta de punto de control a las autoridades");

        String tokenAdmin;

        try {
            UserDto adminDto = getUserByUsername("lebron");
            tokenAdmin = adminDto.getImei();

            FirebaseSender.sendMessage("Alerta - Tiempo de Control ha llegado a 0", "El siguiente usuario puede que tenga problemas: " + username, tokenAdmin);
            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(username);
            serviceResponse.setMensaje("Se ha enviado la alerta de tiempo de control correctamente");
        } catch (FirebaseException | IOException | FirebaseMessagingException e) {
            throw new FirebaseException(username);
        }

        return serviceResponse;
    }
    
    // Algunas funciones para validar requerimientos del proyecto

    //GESTION DEL POSICIONAMIENTO, VALIDAR DISTANCIA Y ZONAS DE SEGURIDAD

    public void validarPosicionamiento(String username, double latitud, double longitud){

        //Primero valido la distancia con el usuario

        MonitoreoElectronicoService monitoreoElectronicoService = new MonitoreoElectronicoService();
        MonitoreoElectronicoDto monitoreoElectronicoDto;
        monitoreoElectronicoDto = monitoreoElectronicoService.getMonitoreoPorUsuario(username);

        UserDto userDtoReq;
        UserDto userDtoUno;
        UserDto userDtoDos;

        CoordenadaDto coordenadaDtoUno;
        CoordenadaDto coordenadaDtoDos;

        ServiceResponse getGeolocationResponse = new ServiceResponse();

        FirebaseSender firebaseSender = new FirebaseSender();

        double distanciaBUsers = 0;

        ZonaDeSeguridadService zonaDeSeguridadService = new ZonaDeSeguridadService();
        ServiceResponse zonaDeSeguridadResponse = new ServiceResponse();
        List<ZonaDeSeguridadDto> responseZonas;

        CoordenadasService coordenadasService = new CoordenadasService();
        ServiceResponse coordenadasResponse = new ServiceResponse();
        List<CoordenadaDto> responseCoord;

        try {

            userDtoReq = getUserByUsername(username);
            userDtoUno = monitoreoElectronicoDto.getAtacanteUser(); //Atacante
            userDtoDos = monitoreoElectronicoDto.getVictimaUser();  //Victima

            getGeolocationResponse = getGoeolocationOfUser(userDtoUno.getUserName());
            coordenadaDtoUno = (CoordenadaDto) getGeolocationResponse.getRespuesta();

            getGeolocationResponse = getGoeolocationOfUser(userDtoDos.getUserName());
            coordenadaDtoDos = (CoordenadaDto) getGeolocationResponse.getRespuesta();

            distanciaBUsers = DistanceCalculator.haversine(coordenadaDtoUno.getLatitud(), coordenadaDtoUno.getLongitud(), coordenadaDtoDos.getLatitud(), coordenadaDtoDos.getLongitud());

            if(distanciaBUsers <= monitoreoElectronicoDto.getDistanciaAlejamiento()){
                try {
                    UserDto adminDto = getUserByUsername("lebron");
                    firebaseSender.sendMessage("Alerta - La distancia de seguridad esta siendo violada", "La distancia entre tu y el agresor es de: " + distanciaBUsers + " km", userDtoDos.getImei());
                    firebaseSender.sendMessage("Alerta - La distancia de seguridad esta siendo violada", "La distancia entre la victima: " + userDtoDos.getUserName() + " y el agresor: " + userDtoUno.getUserName() + " es de: " + distanciaBUsers + " km", adminDto.getImei());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }

            if(userDtoReq.getUserTypeDto().getId() == 3){

                zonaDeSeguridadResponse = zonaDeSeguridadService.getAllZonasByUsername(userDtoReq.getUserName());

                responseZonas = (List<ZonaDeSeguridadDto>) zonaDeSeguridadResponse.getRespuesta();

                for ( ZonaDeSeguridadDto zonaDeSeguridadDto : responseZonas){

                    coordenadasResponse = coordenadasService.getAllCoordZonas(zonaDeSeguridadDto.getId());
                    responseCoord = (List<CoordenadaDto>) coordenadasResponse.getRespuesta();

                    boolean valido = ZonaSegValidator.isPointInPolygon(latitud, longitud, responseCoord);

                    if(valido){
                        UserDto adminDto = getUserByUsername("lebron");
                        firebaseSender.sendMessage("Alerta - Una zona de seguridad ha sido violada", "El agresor se encuentra dentro de la zona de seguridad: " + zonaDeSeguridadDto.getId(), userDtoDos.getImei());
                        firebaseSender.sendMessage("Alerta - Una zona de seguridad ha sido violada", "El agresor: " + userDtoUno.getUserName() + " se encuentra dentro de la zona de seguridad: " + zonaDeSeguridadDto.getId(), adminDto.getImei());
                    }
                }
                

            }

        } catch (PosicionamientoException e) {
            throw new PosicionamientoException(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

    }

    //Funcion necesaria para obtener los movimientos del usuario dado el username, el tiempo actual y el tiempo de inactividad
    @GET
    @Path("/getMovimientoBySecondsUsername/{username}/{segundos}")
    public List<HistoricoMovimientoDto> getMovimientoBySecondsUsername( @PathParam("username") String username, @PathParam("segundos") int segundos ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de historico de movimiento del usuario: "+username);

        List<HistoricoMovimientoDto> response;

        GetAllMovimientoBySecondsUsernameCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in UserService.getAllMovimientoByUsername" );
        //endregion

        try
        {
            UserDto serviceResponseDos = getUserByUsername(username);

            command = CommandFactory.createGetAllMovimientoBySecondsUsernameCommand(serviceResponseDos.getId(), segundos);
            command.execute();
            response = HistoricoMovimientoMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de historicos de movimiento del usuario");

            _logger.info( "Response getAllMovimientoByUsername: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all movimiento {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllMovimientoByUsername" );

        return response;
    }

    public boolean validarMovimiento(String username, int inactividad){

        boolean condition = false;

        List<HistoricoMovimientoDto> movimiento = getMovimientoBySecondsUsername(username, inactividad);

        for (HistoricoMovimientoDto historicoMovimientoDto : movimiento){

            if (historicoMovimientoDto.isMovimiento()){
                condition = true;
            }
        }

        return condition;
    }

}
