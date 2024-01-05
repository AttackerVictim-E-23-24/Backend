package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCoordenadaByUsernameIdCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( GetCoordenadaByUsernameIdCommand.class );
    private Coordenada _coordenada;
    private CoordenadaDao _dao;

    public GetCoordenadaByUsernameIdCommand(Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetCoordenadaByUsernameIdCommand.ctor: parameter {%s}", coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(new DBHandler());
        _dao = DaoFactory.createCoordenadaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetCoordenadaByUsernameIdCommand.ctor: atribute {%s}", _coordenada.toString() ) );
        //endregion
    }

    public GetCoordenadaByUsernameIdCommand(Coordenada coordenada, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetCoordenadaByUsernameIdCommand.ctor: parameter {%s}", coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(handler);
        _dao = DaoFactory.createCoordenadaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetCoordenadaByUsernameIdCommand.ctor: atribute {%s}", _coordenada.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetCoordenadaByUsernameIdCommand.execute" );
        //endregion
        _coordenada = _dao.getUltimaCoordenadaByUsuarioId(_coordenada.getUsuario().getId());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetCoordenadaByUsernameIdCommand.execute" );
        //endregion
    }

    @Override
    public Coordenada getReturnParam()
    {
        return _coordenada;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
