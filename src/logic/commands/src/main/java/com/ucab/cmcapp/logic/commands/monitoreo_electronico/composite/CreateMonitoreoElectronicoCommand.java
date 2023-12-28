package com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.AddMonitoreoElectronicoCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateMonitoreoElectronicoCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger( CreateMonitoreoElectronicoCommand.class );
    private MonitoreoElectronico _monitoreoElectronico;
    private MonitoreoElectronico _result;
    private AddMonitoreoElectronicoCommand _addMonitoreoElectronicoCommand;

    public CreateMonitoreoElectronicoCommand(MonitoreoElectronico monitoreoElectronico )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateMonitoreoElectronicoCommand.ctor");
        //endregion

        _monitoreoElectronico = monitoreoElectronico;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateMonitoreoElectronicoCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateMonitoreoElectronicoCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addMonitoreoElectronicoCommand = CommandFactory.createAddMonitoreoElectronicoCommand(_monitoreoElectronico, getHandler());
            _addMonitoreoElectronicoCommand.execute();
            _result = _addMonitoreoElectronicoCommand.getReturnParam();
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
        _logger.debug( "Leaving CreateMonitoreoElectronicoCommand.execute");
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
