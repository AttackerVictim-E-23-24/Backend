package com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.DelMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.DelZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.DeleteZonaDeSeguridadCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteMonitoreoCommand extends Command< MonitoreoElectronico > {

    private static Logger _logger = LoggerFactory.getLogger( DeleteMonitoreoCommand.class );
    private MonitoreoElectronico _monitoreo;
    private MonitoreoElectronico _result;
    private DelMonitoreoCommand _DelMonitoreoCommand;

    public DeleteMonitoreoCommand(MonitoreoElectronico monitoreo )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering DeleteMonitoreoCommand.ctor");
        //endregion

        _monitoreo = monitoreo;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DeleteMonitoreoCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering DeleteMonitoreoCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _DelMonitoreoCommand = CommandFactory.createDelMonitoreoCommand(_monitoreo, getHandler());
            _DelMonitoreoCommand.execute();
            _result = _DelMonitoreoCommand.getReturnParam();
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
        _logger.debug( "Leaving DeleteMonitoreoCommand.execute");
        //endregion
    }

    @Override
    public MonitoreoElectronico getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
