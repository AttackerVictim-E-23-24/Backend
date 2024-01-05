package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddCoordenadaCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( AddCoordenadaCommand.class );
    private Coordenada _coordenada;
    private CoordenadaDao _dao;

    public AddCoordenadaCommand( Coordenada coordenada, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddCoordenadaCommand.ctor: parameter {%s}",
                coordenada.toString() ) );
        setHandler(handler);
        _coordenada = coordenada;
        _dao = DaoFactory.createCoordenadaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddCoordenadaCommand.ctor: attribute {%s}",
                _coordenada.toString() ) );
        //endregion
    }

    public AddCoordenadaCommand( Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddCoordenadaCommand.ctor: parameter {%s}",
                coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(new DBHandler());
        _dao = DaoFactory.createCoordenadaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddCoordenadaCommand.ctor: attribute {%s}",
                _coordenada.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddCoordenadaCommand.execute" );
        //endregion

        _coordenada = _dao.insert( _coordenada);

        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddCoordenadaCommand.execute" );
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