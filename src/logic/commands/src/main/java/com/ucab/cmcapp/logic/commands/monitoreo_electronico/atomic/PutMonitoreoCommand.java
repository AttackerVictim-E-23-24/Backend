package com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.PutCoordenadaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutMonitoreoCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger(PutMonitoreoCommand.class );
    private MonitoreoElectronico _monitoreo;
    private MonitoreoElectronicoDao _dao;

    public PutMonitoreoCommand( MonitoreoElectronico monitoreoElectronico, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutMonitoreoCommand.ctor: parameter {%s}",
                monitoreoElectronico.toString() ) );
        setHandler(handler);
        _monitoreo = monitoreoElectronico;
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutMonitoreoCommand.ctor: attribute {%s}",
                _monitoreo.toString() ) );
        //endregion
    }

    public PutMonitoreoCommand( MonitoreoElectronico monitoreoElectronico)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutMonitoreoCommand.ctor: parameter {%s}",
                monitoreoElectronico.toString() ) );
        _monitoreo = monitoreoElectronico;
        setHandler(new DBHandler());
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutMonitoreoCommand.ctor: attribute {%s}",
                _monitoreo.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  PutMonitoreoCommand.execute" );
        //endregion

        _monitoreo = _dao.update( _monitoreo );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving  PutMonitoreoCommand.execute" );
        //endregion
    }

    @Override
    public MonitoreoElectronico getReturnParam()
    {
        return _monitoreo;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
