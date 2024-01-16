package com.ucab.cmcapp.logic.commands.zona_seguridad.atomic;

import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllZonasBUserCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllZonasBUserCommand.class );
    private List<ZonaDeSeguridad> _result;
    private ZonaDeSeguridadDao _dao;

    private long _idMonitoreo;

    public GetAllZonasBUserCommand(DBHandler handler, long idMonitoreo)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllZonasBUserCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createZonaDeSeguridadDao(getHandler());
        _idMonitoreo = idMonitoreo;

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllZonasBUserCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllZonasBUserCommand.execute" );
        //endregion
        _result = _dao.getZonasSeguridadByMonitoreoId(_idMonitoreo);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllZonasBUserCommand.execute" );
        //endregion
    }

    @Override
    public List<ZonaDeSeguridad> getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }


}
