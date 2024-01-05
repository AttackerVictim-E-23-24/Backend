package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutCoordenadaCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger(PutCoordenadaCommand.class );
    private Coordenada _coordenada;
    private CoordenadaDao _dao;

    public PutCoordenadaCommand( Coordenada coordenada, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutCoordenadaCommand.ctor: parameter {%s}",
                coordenada.toString() ) );
        setHandler(handler);
        _coordenada = coordenada;
        _dao = DaoFactory.createCoordenadaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutCoordenadaCommand.ctor: attribute {%s}",
                _coordenada.toString() ) );
        //endregion
    }

    public PutCoordenadaCommand( Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutCoordenadaCommand.ctor: parameter {%s}",
                coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(new DBHandler());
        _dao = DaoFactory.createCoordenadaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutCoordenadaCommand.ctor: attribute {%s}",
                _coordenada.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  PutCoordenadaCommand.execute" );
        //endregion

        _coordenada = _dao.update( _coordenada);

        //region Instrumentation DEBUG
        _logger.debug( "Leaving  PutCoordenadaCommand.execute" );
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
