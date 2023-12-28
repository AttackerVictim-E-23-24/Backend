package com.ucab.cmcapp.logic.commands.zona_seguridad.composite;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.AddMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.CreateMonitoreoElectronicoCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.AddZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.dtos.ZonaDeSeguridadDto;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateZonaDeSeguridadCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( CreateZonaDeSeguridadCommand.class );
    private ZonaDeSeguridad _zonaDeSeguridad;
    private ZonaDeSeguridad _result;
    private AddZonaDeSeguridadCommand _addZonaDeSeguridadCommand;

    public CreateZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateZonaDeSeguridadCommand.ctor");
        //endregion

        _zonaDeSeguridad = zonaDeSeguridad;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateZonaDeSeguridadCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateZonaDeSeguridadCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addZonaDeSeguridadCommand = CommandFactory.createAddZonaDeSeguridadCommand(_zonaDeSeguridad, getHandler());
            _addZonaDeSeguridadCommand.execute();
            _result = _addZonaDeSeguridadCommand.getReturnParam();
            getHandler().finishTransaction();
            getHandler().closeSession();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateZonaDeSeguridadCommand.execute");
        //endregion
    }

    @Override
    public ZonaDeSeguridad getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }

}
