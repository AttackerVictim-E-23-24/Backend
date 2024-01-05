package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.common.util.ServiceResponse;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.GetCoordenadaByLatLonCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.GetCoordenadasZonaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.UpdateCoordenadaCommand;
import com.ucab.cmcapp.logic.dtos.CoordenadaDto;
import com.ucab.cmcapp.logic.mappers.CoordenadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/coordenadas" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CoordenadasService extends BaseService{
    private static Logger _logger = LoggerFactory.getLogger( CoordenadasService.class );

    //Metodo para obtener la lista de coordenadas de una ZONA DE SEGURIDAD

    @GET
    @Path( "/getCoordZonaSeg/{zonaSegId}" )
    public ServiceResponse getAllCoordZonas(@PathParam( "zonaSegId" ) long zonaSegId ) {

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setMensaje("No se podido obtener la lista de coordenadas de la zona de seguridad");

        List<CoordenadaDto> response;

        GetCoordenadasZonaCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in CoordenadasService.getAllCoordZonas: "+zonaSegId );
        //endregion

        try
        {
            command = CommandFactory.createGetCoordenadasZonaCommand(zonaSegId);
            command.execute();
            response = CoordenadaMapper.mapEntityToDto( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se obtuvo la lista de coordenadas de la zona de seguridad");

            _logger.info( "Response getAllCoordZonas: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting all coordenadas de la zona {}: {}", e.getMessage(), zonaSegId, e.getCause());
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
    @Path( "/getMonitoreo/{latitud}/{longitud}/{zonaSegId}" )
    public CoordenadaDto getCoordenada(@PathParam( "latitud" ) double latitud, @PathParam( "longitud" ) double longitud, @PathParam( "zonaSegId" ) long zonaSegId)
    {

        CoordenadaDto response;
        Coordenada entity;
        GetCoordenadaByLatLonCommand command = null;

        //region Instrumentation DEBUG
        _logger.debug( "Get in Get in CoordenadasService.getCoordenada: "+latitud+" "+longitud +" "+zonaSegId );
        //endregion

        try
        {

            entity = CoordenadaMapper.mapDtoToEntity( latitud, longitud, zonaSegId );
            command = CommandFactory.createGetCoordenadaByLatLonCommand( entity );
            command.execute();

            response = CoordenadaMapper.mapEntityToDto( command.getReturnParam());
            _logger.info( "Response getCoordenada: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} getting coordenada {}: {}", e.getMessage(), latitud, longitud, e.getCause());

            //Aqui deberia hacer el handler de las excepciones y utilizar la respuesta customizada

            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving CoordenadasService.getCoordenada" );
        return response;
    }

    @PUT
    @Path( "/putCoordZonaSeg/{zonaSegId}/{latitud}/{longitud}/{newLatitud}/{newLongitud}" )
    public ServiceResponse putCoordZonaSeg( @PathParam( "zonaSegId" ) long zonaSegId, @PathParam( "latitud" ) double latitud, @PathParam( "longitud" ) double longitud, @PathParam( "newLatitud" ) double newLatitud, @PathParam( "newLongitud" ) double newLongitud){

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(false);
        serviceResponse.setRespuesta("No se pudo modificar la coordenada");

        Coordenada entity;
        CoordenadaDto response;
        UpdateCoordenadaCommand command = null;

        CoordenadaDto coordenadaDto;

        //Primero tengo buscar la coordenada, modificarla y luego aplico los comandos para updatearla

        coordenadaDto = getCoordenada(latitud, longitud, zonaSegId);  // ----Puede que necesite implementar un Try and Catch

        if (newLatitud != 0){
            coordenadaDto.setLatitud(newLatitud);
        }

        if (newLongitud != 0){
            coordenadaDto.setLongitud(newLongitud);
        }

        //region Instrumentation DEBUG
        _logger.debug( "Get in CoordenadasService.putCoordZonaSeg" );
        //endregion

        try
        {
            entity = CoordenadaMapper.mapDtoToEntity( coordenadaDto );
            command = CommandFactory.createUpdateCoordenadaCommand( entity );
            command.execute();
            response = CoordenadaMapper.mapEntityToDtoUpdCoord( command.getReturnParam() );

            serviceResponse.setStatus(true);
            serviceResponse.setRespuesta(response);
            serviceResponse.setMensaje("Se modifico la coordenada correctamente");
            _logger.info( "Response putCoordZonaSeg: {} ", response );
        }
        catch ( Exception e )
        {
            _logger.error("error {} updating coordenada: {}", e.getMessage(), e.getCause());
            throw new WebApplicationException( Response.status( Response.Status.INTERNAL_SERVER_ERROR ).
                    entity( e ).build() );
        }
        finally
        {
            if (command != null)
                command.closeHandlerSession();
        }

        _logger.debug( "Leaving CoordenadasService.putCoordZonaSeg" );
        return serviceResponse;

    }




}