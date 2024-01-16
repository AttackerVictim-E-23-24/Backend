package com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.DelZonaDeSeguridadCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelMonitoreoCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger( DelMonitoreoCommand.class );
    private MonitoreoElectronico _monitoreo;
    private MonitoreoElectronicoDao _dao;

    public DelMonitoreoCommand( MonitoreoElectronico monitoreo, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in DelMonitoreoCommand.ctor: parameter {%s}",
                monitoreo.toString() ) );
        setHandler(handler);
        _monitoreo = monitoreo;
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving DelMonitoreoCommand.ctor: attribute {%s}",
                _monitoreo.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  DelMonitoreoCommand.execute" );
        //endregion

        _monitoreo = _dao.update( _monitoreo );

        //region Instrumentation DEBUG
        _logger.debug( "Get in  DelMonitoreoCommand.execute" );
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
