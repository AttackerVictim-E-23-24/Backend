package com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMonitoreoElectronicoCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger( AddMonitoreoElectronicoCommand.class );
    private MonitoreoElectronico _monitoreoElectronico;
    private MonitoreoElectronicoDao _dao;

    public AddMonitoreoElectronicoCommand( MonitoreoElectronico monitoreoElectronico, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddMonitoreoElectronicoCommand.ctor: parameter {%s}",
                monitoreoElectronico.toString() ) );
        setHandler(handler);
        _monitoreoElectronico = monitoreoElectronico;
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddMonitoreoElectronicoCommand.ctor: attribute {%s}",
                _monitoreoElectronico.toString() ) );
        //endregion
    }

    public AddMonitoreoElectronicoCommand( MonitoreoElectronico monitoreoElectronico )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddMonitoreoElectronicoCommand.ctor: parameter {%s}",
                monitoreoElectronico.toString() ) );
        _monitoreoElectronico = monitoreoElectronico;
        setHandler(new DBHandler());
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddMonitoreoElectronicoCommand.ctor: attribute {%s}",
                _monitoreoElectronico.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddMonitoreoElectronicoCommand.execute" );
        //endregion

        _monitoreoElectronico = _dao.insert( _monitoreoElectronico);

        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddMonitoreoElectronicoCommand.execute" );
        //endregion
    }

    @Override
    public MonitoreoElectronico getReturnParam()
    {
        return _monitoreoElectronico;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }



}
