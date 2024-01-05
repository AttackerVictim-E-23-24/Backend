package com.ucab.cmcapp.logic.commands.zona_seguridad.atomic;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.atomic.GetAllUsersWPCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UserDao;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllZonasWPCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllZonasWPCommand.class );
    private List<ZonaDeSeguridad> _result;
    private ZonaDeSeguridadDao _dao;

    public GetAllZonasWPCommand(DBHandler handler)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllZonasWPCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createZonaDeSeguridadDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllZonasWPCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllZonasWPCommand.execute" );
        //endregion
        _result = _dao.findAll(ZonaDeSeguridad.class);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllZonasWPCommand.execute" );
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
