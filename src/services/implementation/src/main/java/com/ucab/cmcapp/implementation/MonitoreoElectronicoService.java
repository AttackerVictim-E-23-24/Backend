package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.common.util.HilosInamovilidad;
import com.ucab.cmcapp.common.util.ServiceResponse;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.GetMonitoreoByCedulaCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.GetMonitoreoByCedulaNombreUsuarioCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.CreateMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.DeleteMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.UpdateMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.DeleteZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import com.ucab.cmcapp.logic.mappers.MonitoreoElectronicoMapper;
import com.ucab.cmcapp.logic.mappers.ZonaDeSeguridadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path( "/monitoreo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class MonitoreoElectronicoService extends BaseService{

    private static Logger _logger = LoggerFactory.getLogger( UserService.class );

    @POST
    @Path( "/setMonitoreo" )
    public ServiceResponse addMonitoreo(MonitoreoElectronicoDto monitoreoElectronicoDto)
    {
        MonitoreoElectronico entity;
        MonitoreoElectronicoDto response;
        CreateMonitoreoElectronicoCommand command = null;

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido agregar el monitoreo electronico");

        HiloInamovilidad hiloInamovilidad = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoService.addMonitoreo" );
        //endregion

        //Primero se tienen que buscar los usuarios a los cuales va referenciado el monitoreo electronico,
        //para eso, primero se busca a la persona por la cedula y luego al usuario por el idPersona

        try
        {

            long idAtacante = PersonaService.getPersona( monitoreoElectronicoDto.getCedulaAtacante() );
            long idVictima = PersonaService.getPersona( monitoreoElectronicoDto.getCedulaVictima() );

            UserDto atacante = new UserDto();
            UserDto victima = new UserDto();

            atacante.setId( idAtacante );
            victima.setId( idVictima );

            monitoreoElectronicoDto.setAtacanteUser( atacante );
            monitoreoElectronicoDto.setVictimaUser( victima );


            entity = MonitoreoElectronicoMapper.mapDtoToEntity( monitoreoElectronicoDto );

            // Lineas para evitar el error por usuarios nulos
            User userAtac = EntityFactory.createUser(idAtacante);
            User userVict = EntityFactory.createUser(idVictima);

            entity.setAtacanteUser(userAtac);
            entity.setVictimaUser(userVict);
            entity.setActive(true);

            // Se porsigue con la logica del programa

            command = CommandFactory.createCreateMonitoreoElectronicoCommand( entity );
            command.execute();
            response = MonitoreoElectronicoMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setRespuesta( response );
            serviceResponse.setMensaje("Se ha agregado correctamente el monitoreo electronico");
            serviceResponse.setStatus(true);

            // Mando a correr el hilo para verificar la inamovilidad de la vicitma -- INAMOVILIDAD

            hiloInamovilidad = new HiloInamovilidad(entity.getTiempoInactividad(), userVict.getUserName());

            hiloInamovilidad.start();

            HilosInamovilidad.getThreads().add(hiloInamovilidad);

            _logger.info( "Response addMonitoreo: {} ", response );

        }
        catch ( Exception e )
        {
            _logger.error("error {} adding monitoreo: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving MonitoreoElectronicoService.addMonitoreo" );
        return serviceResponse;
    }

    @GET
    @Path( "/getMonitoreo/{cedulaAtacante}/{cedulaVictima}" )
    public MonitoreoElectronicoDto getMonitoreo(@PathParam( "cedulaAtacante" ) long cedulaAtacante, @PathParam( "cedulaVictima" ) long cedulaVictima)
    {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido obtener el monitoreo electronico");

        MonitoreoElectronico entity;
        MonitoreoElectronicoDto response = new MonitoreoElectronicoDto();

        GetMonitoreoByCedulaCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoService.getMonitoreo" );
        //endregion

        try
        {

            entity = MonitoreoElectronicoMapper.mapDtoToEntityByCedula( cedulaAtacante, cedulaVictima );
            command = CommandFactory.createGetMonitoreoByCedulaCommand( entity );
            command.execute();
            response = MonitoreoElectronicoMapper.mapEntityToDto( command.getReturnParam());
            serviceResponse.setRespuesta( response );
            _logger.info( "Response getMonitoreo: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting monitoreo {}: {}", e.getMessage(), cedulaAtacante, cedulaVictima, e.getCause());

            //Aqui deberia hacer el handler de las excepciones y utilizar la respuesta customizada

            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving MonitoreoElectronicoService.getMonitoreo" );
        return response;
    }

    @GET
    @Path( "/getMonitoreo/{nombreUsuario}/" )
    public MonitoreoElectronicoDto getMonitoreoPorUsuario(@PathParam( "nombreUsuario" ) String nombreUsuario)
    {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido obtener el monitoreo electronico");

        MonitoreoElectronico entity;
        MonitoreoElectronicoDto response = new MonitoreoElectronicoDto();

        GetMonitoreoByCedulaNombreUsuarioCommand command = null;
        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoService.getMonitoreoPorUsuario" );
        //endregion

        try
        {
            //Dado el nombre de usuario, se extrae la cedula correspondiente y se asigna a cualquiera (atacante o victima)

            UserService userService = new UserService();
            UserDto user = userService.getUserByUsername( nombreUsuario );

            long cedulaUsuario = user.getDatosPersona().getCedula();

            entity = MonitoreoElectronicoMapper.mapDtoToEntityByCedulaNombreUsuario( cedulaUsuario);
            command = CommandFactory.createGetMonitoreoByCedulaNombreUsuarioCommand( entity );
            command.execute();
            response = MonitoreoElectronicoMapper.mapEntityToDto( command.getReturnParam());

            serviceResponse.setRespuesta( response );
            serviceResponse.setStatus(true);
            serviceResponse.setMensaje("Se ha obtenido el monitoreo electronico correctamente");

            _logger.info( "Response getMonitoreo: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting monitoreo by username{}: {}", e.getMessage(), nombreUsuario, e.getCause());

            //Aqui deberia hacer el handler de las excepciones y utilizar la respuesta customizada

            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving MonitoreoElectronicoService.getMonitoreoPorUsuario" );
        return response;
    }

    @PUT
    @Path( "/putMonitoreo/{username}" )
    public ServiceResponse putMonitoreo( @PathParam( "username" ) String username, MonitoreoElectronicoDto monitoreoElectronicoDto)
    {
        MonitoreoElectronico entity;
        MonitoreoElectronicoDto updatedMonitoreo = new MonitoreoElectronicoDto();
        MonitoreoElectronicoDto response;
        UpdateMonitoreoCommand command = null;

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido actualizar el monitoreo electronico");

        HiloInamovilidad hiloInamovilidad = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoService.putMonitoreo" );
        //endregion

        try
        {
            updatedMonitoreo = getMonitoreoPorUsuario( username );

            updatedMonitoreo.setDistanciaAlejamiento( monitoreoElectronicoDto.getDistanciaAlejamiento());
            updatedMonitoreo.setTiempoInactividad( monitoreoElectronicoDto.getTiempoInactividad());
            updatedMonitoreo.setFrecuencia( monitoreoElectronicoDto.getFrecuencia());
            updatedMonitoreo.setTiempoOffline( monitoreoElectronicoDto.getTiempoOffline());

            entity = MonitoreoElectronicoMapper.mapDtoToEntity( updatedMonitoreo );

            User userAtac = EntityFactory.createUser(updatedMonitoreo.getAtacanteUser().getId());
            User userVict = EntityFactory.createUser(updatedMonitoreo.getVictimaUser().getId());

            entity.setAtacanteUser(userAtac);
            entity.setVictimaUser(userVict);

            entity.setActive(true);

            command = CommandFactory.createUpdateMonitoreoCommand( entity );
            command.execute();

            serviceResponse.setRespuesta( command.getReturnParam() );
            serviceResponse.setMensaje("Se ha actualizado correctamente el monitoreo electronico");
            serviceResponse.setStatus(true);

            // Se hace la gestion para parar cualquier hilo que este corriendo para lanzar uno nuevo

            // Mando a correr el hilo para verificar la inamovilidad de la vicitma -- INAMOVILIDAD



            hiloInamovilidad = new HiloInamovilidad(entity.getTiempoInactividad(), userVict.getUserName());

            hiloInamovilidad.start();

            List<Thread> threads = HilosInamovilidad.getThreads();

            for (Thread thread : threads) {

                if (thread.isAlive()) {
                    thread.interrupt();
                }

            }

            HilosInamovilidad.getThreads().add(hiloInamovilidad);

            _logger.info( "Response putMonitoreo: {} ", command.getReturnParam() );

        }
        catch ( Exception e )
        {
            _logger.error("error {} updating monitoreo: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving MonitoreoElectronicoService.putMonitoreo" );
        return serviceResponse;
    }

    @DELETE
    @Path( "/deleteMonitoreo/{username}" )
    public ServiceResponse deleteMonitoreo( @PathParam( "username" ) String username ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(null);
        serviceResponse.setMensaje("No se ha podido eliminar el monitoreo electronico");

        MonitoreoElectronico entity;
        MonitoreoElectronicoDto response;
        DeleteMonitoreoCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in MonitoreoElectronicoService.deleteMonitoreo" );
        //endregion

        try
        {
            response = getMonitoreoPorUsuario( username );

            entity = MonitoreoElectronicoMapper.mapDtoToEntity( response );

            long idAtacante = PersonaService.getPersona( response.getCedulaAtacante() );
            long idVictima = PersonaService.getPersona( response.getCedulaVictima() );

            // Lineas para evitar el error por usuarios nulos
            User userAtac = EntityFactory.createUser(idAtacante);
            User userVict = EntityFactory.createUser(idVictima);

            entity.setAtacanteUser(userAtac);
            entity.setVictimaUser(userVict);
            entity.setActive(false);

            command = CommandFactory.createDeleteMonitoreoCommand( entity );
            command.execute();

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(true);
            serviceResponse.setMensaje("Monitoreo Electronico eliminado correctamente");

            _logger.info( "Response deleteMonitoreo: {} ", true );
        }
        catch ( Exception e )
        {
            _logger.error("error {} deleting monitoreo {}: {}", e.getMessage(), username, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving MonitoreoElectronicoService.deleteMonitoreo" );
        return serviceResponse;

    }


}
