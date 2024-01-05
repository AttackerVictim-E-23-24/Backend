package com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetMonitoreoByCedulaNombreUsuarioCommand extends Command<MonitoreoElectronico> {

    private static Logger _logger = LoggerFactory.getLogger( GetMonitoreoByCedulaNombreUsuarioCommand.class );
    private MonitoreoElectronico _monitoreo;
    private MonitoreoElectronicoDao _dao;

    public GetMonitoreoByCedulaNombreUsuarioCommand (MonitoreoElectronico monitoreoElectronico )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetMonitoreoByCedulaCommand.ctor: parameter {%s}", monitoreoElectronico.toString() ) );
        _monitoreo = monitoreoElectronico;
        setHandler(new DBHandler());
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetMonitoreoByCedulaCommand.ctor: atribute {%s}", _monitoreo.toString() ) );
        //endregion
    }

    public GetMonitoreoByCedulaNombreUsuarioCommand (MonitoreoElectronico monitoreoElectronico, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetMonitoreoByCedulaCommand.ctor: parameter {%s}", monitoreoElectronico.toString() ) );
        _monitoreo = monitoreoElectronico;
        setHandler(handler);
        _dao = DaoFactory.createMonitoreoElectronicoDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetMonitoreoByCedulaCommand.ctor: atribute {%s}", _monitoreo.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetMonitoreoByCedulaCommand. execute" );
        //endregion
        _monitoreo= _dao.getMonitoreoByCedulaNombreUsuario(_monitoreo.getCedulaAtacante());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetPersonaByCedulaCommand.execute" );
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
