package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.atomic.GetAllUsersWPCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetCoordenadasZonasByIdCommand extends Command<Coordenada> {
    private static Logger _logger = LoggerFactory.getLogger( GetCoordenadasZonasByIdCommand.class );
    private long _zonaId;
    private List<Coordenada> _result;
    private CoordenadaDao _dao;

    public GetCoordenadasZonasByIdCommand(DBHandler handler, long zonaId)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetCoordenadasZonasByIdCommand.ctor: parameter {%s}", zonaId ) );
        //endregion

        _zonaId = zonaId;
        setHandler(handler);
        _dao = DaoFactory.createCoordenadaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetCoordenadasZonasByIdCommand.ctor: attribute {%s}", zonaId) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetCoordenadasZonasByIdCommand.execute" );
        //endregion
        _result = _dao.findAllByZonaId(_zonaId);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetCoordenadasZonasByIdCommand.execute" );
        //endregion
    }

    @Override
    public List<Coordenada> getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
