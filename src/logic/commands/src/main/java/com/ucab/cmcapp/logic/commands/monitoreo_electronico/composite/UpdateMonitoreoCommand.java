package com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.PutCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.UpdateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.PutMonitoreoCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMonitoreoCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger( UpdateMonitoreoCommand.class );
    private MonitoreoElectronico _monitoreoElectronico;
    private MonitoreoElectronico _result;
    private PutMonitoreoCommand _putMonitoreoCommand;

    public UpdateMonitoreoCommand(MonitoreoElectronico monitoreoElectronico )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateMonitoreoCommand.ctor");
        //endregion

        _monitoreoElectronico = monitoreoElectronico;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UpdateMonitoreoCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateMonitoreoCommand.execute ");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _putMonitoreoCommand = CommandFactory.createPutMonitoreoCommand(_monitoreoElectronico, getHandler());
            _putMonitoreoCommand.execute();
            _result = _putMonitoreoCommand.getReturnParam();
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
        _logger.debug( "Leaving UpdateMonitoreoCommand.execute");
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
