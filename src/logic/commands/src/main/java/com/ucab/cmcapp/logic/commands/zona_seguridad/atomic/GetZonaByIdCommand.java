package com.ucab.cmcapp.logic.commands.zona_seguridad.atomic;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByIdCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UserDao;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetZonaByIdCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( GetZonaByIdCommand.class );
    private long _zonaId;
    private ZonaDeSeguridad _result;
    private ZonaDeSeguridadDao _dao;

    public GetZonaByIdCommand(DBHandler handler, long zonaId )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetZonaByIdCommand.ctor: parameter {%s}", zonaId ) );
        //endregion

        _zonaId = zonaId;
        setHandler(handler);
        _dao = DaoFactory.createZonaDeSeguridadDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetZonaByIdCommand.ctor: attribute {%s}", _zonaId ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetZonaByIdCommand.execute" );
        //endregion
        _result = _dao.find(_zonaId, ZonaDeSeguridad.class);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetZonaByIdCommand.execute" );
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
