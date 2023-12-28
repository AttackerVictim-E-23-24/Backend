package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.common.util.LDAP;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.CreateZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.dtos.MonitoreoElectronicoDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import com.ucab.cmcapp.logic.mappers.UserMapper;
import com.ucab.cmcapp.logic.mappers.ZonaDeSeguridadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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



}
