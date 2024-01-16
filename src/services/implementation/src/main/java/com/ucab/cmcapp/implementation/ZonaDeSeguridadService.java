package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.common.util.ServiceResponse;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.composite.CreateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.*;
import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import com.ucab.cmcapp.logic.mappers.CoordenadaMapper;
import com.ucab.cmcapp.logic.mappers.ZonaDeSeguridadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/zonasSeg" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ZonaDeSeguridadService extends BaseService{

    private static Logger _logger = LoggerFactory.getLogger( MonitoreoElectronicoService.class );

    @POST
    @Path( "/setZonaSeg" )
    public ServiceResponse addZona(ZonaDeSeguridadDto zonaDeSeguridadDto)
    {
        ZonaDeSeguridad entity;
        ZonaDeSeguridadDto response;
        CreateZonaDeSeguridadCommand command = null;

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se ha podido agregar la zona de seguridad");

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonasSeguridadService.addZona" );
        //endregion

        //Primero se tiene que buscar el monitoreo al cual va referenciada la zona de seguridad,
        //para eso, se busca el monitoreo por la cedula del atacante y por la cedula ed la victima

        MonitoreoElectronicoService monitoreoService = new MonitoreoElectronicoService();

        try
        {
            //Obtengo el id del monitoreo
            MonitoreoElectronicoDto serviceResponseDos = monitoreoService.getMonitoreo(zonaDeSeguridadDto.getMonitoreo().getCedulaAtacante(), zonaDeSeguridadDto.getMonitoreo().getCedulaVictima());
            zonaDeSeguridadDto.setMonitoreo(serviceResponseDos);
            entity = ZonaDeSeguridadMapper.mapDtoToEntity( zonaDeSeguridadDto );
            command = CommandFactory.createCreateZonaDeSeguridadCommand( entity );
            command.execute();
            response = ZonaDeSeguridadMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Zona de seguridad agregada correctamente");

            _logger.info( "Response addZona: {} ", response );

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
    @Path( "/getAll" )
    public ServiceResponse getAllZonas() {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de zonas de seguridad");

        List<ZonaDeSeguridadDto> response;

        GetAllZonasCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadService.getAllZonas" );
        //endregion

        try
        {
            command = CommandFactory.createGetAllZonasCommand();
            command.execute();
            response = ZonaDeSeguridadMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de zonas de seguridad");

            _logger.info( "Response getAllZonas: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all zonas {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllZonas" );

        return serviceResponse;
    }

    @GET
    @Path( "/getAllByUsername/{username}" )
    public ServiceResponse getAllZonasByUsername( @PathParam( "username" ) String username ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de zonas de seguridad del usuario: "+username);

        List<ZonaDeSeguridadDto> response;

        GetAllZonasByUsernameCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadService.getAllZonas" );
        //endregion

        try
        {
            MonitoreoElectronicoService monitoreoService = new MonitoreoElectronicoService();
            MonitoreoElectronicoDto serviceResponseDos = monitoreoService.getMonitoreoPorUsuario(username);

            command = CommandFactory.createGetAllZonasByUsernameCommand(serviceResponseDos.getId());
            command.execute();
            response = ZonaDeSeguridadMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de zonas de seguridad");

            _logger.info( "Response getAllZonas: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all zonas {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllZonas" );

        return serviceResponse;
    }

    @GET
    @Path( "/getZona/{id}" )
    public ZonaDeSeguridadDto getZona( @PathParam( "id" ) long id ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la de zona de seguridad");

        ZonaDeSeguridadDto response;

        GetZonaCommand command = null;
        ZonaDeSeguridad entity;

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadService.getZonaById" );
        //endregion

        try
        {
            entity = ZonaDeSeguridadMapper.mapDtoToEntity( id );
            command = CommandFactory.createGetZonaCommand(entity);
            command.execute();
            response = ZonaDeSeguridadMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de zonas de seguridad");

            _logger.info( "Response getAllZonas: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all zonas {}: {}", e.getMessage(), "getAll", e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving UserService.getAllZonas" );

        return response;
    }

    @DELETE
    @Path( "/deleteZonaSeg/{zonaSegId}" )
    public ServiceResponse deleteZona( @PathParam( "zonaSegId" ) long zonaSegId ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta(null);
        serviceResponse.setMensaje("No se ha podido eliminar la zona de seguridad");

        ZonaDeSeguridad entity;
        ZonaDeSeguridadDto response;
        DeleteZonaDeSeguridadCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadService.deleteZona" );
        //endregion

        try
        {
            entity = ZonaDeSeguridadMapper.mapDtoToEntity(getZona( zonaSegId ));
            entity.setActivo(false);
            command = CommandFactory.createDeleteZonaDeSeguridadCommand( entity );
            command.execute();

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(true);
            serviceResponse.setMensaje("Zona de seguridad eliminada correctamente");

            _logger.info( "Response deleteZona: {} ", true );
        }
        catch ( Exception e )
        {
            _logger.error("error {} deleting zona {}: {}", e.getMessage(), zonaSegId, e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving ZonaDeSeguridadService.deleteZona" );
        return serviceResponse;

    }

    @POST
    @Path( "/setGeolocationZona" )
    public ServiceResponse addLocalizacion(ArrayList<CoordenadaDto> listDeCoordenadas)
    {

        ServiceResponse serviceResponse = new ServiceResponse();

        ArrayList<CoordenadaDto> coordenadasAgregadas = new ArrayList<CoordenadaDto>();

        Coordenada entity;
        CoordenadaDto response;
        CreateCoordenadaCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in ZonaDeSeguridadService.addLozalizacion" );
        //endregion

        //Recorro el Array de Coordenadas y las registro

        for (CoordenadaDto coordenada : listDeCoordenadas){

                //La coordenada corresponde a una zona de seguridad

                try
                {
                    _logger.debug( "Get in ZonaDeSeguridadService.addLozalizacion - Coordenada de Zona de Seguridad" );
                   coordenada.setZonaSegDto( getZona( coordenada.getZonaSegDto().getId() ) );

                    entity = CoordenadaMapper.mapDtoToEntity( coordenada );
                    command = CommandFactory.createCreateCoordenadaCommand( entity );
                    command.execute();
                    response = CoordenadaMapper.mapEntityToDto( command.getReturnParam() );

                    coordenadasAgregadas.add( response );

                    serviceResponse.setRespuesta( coordenadasAgregadas );
                    serviceResponse.setStatus(true); //Todo correcto
                    serviceResponse.setMensaje("Se ha registrado la ubicacion del punto de la Zona de Seguridad");

                    _logger.info( "Response addCoordenada Zona: {} ", response );

                }
                catch ( Exception e )
                {
                    _logger.error("error {} adding coordenada zona: {}", e.getMessage(), e.getCause());
                    serviceResponse.setStatus(false);
                    serviceResponse.setMensaje("Error al registrar las coordenadas");
                    throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                            entity( e ).build() );
                }
                finally
                {
                    if (command != null)
                        command.closeHandlerSession();
                }

        }

        _logger.debug( "Leaving ZonaDeSeguridadService.addLozalizacion - Zona de Seguridad" );
        return serviceResponse;
    }


}
