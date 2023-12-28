package com.ucab.cmcapp.logic.commands.persona.atomic;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddPersonaCommand extends Command<Persona>
{
    private static Logger _logger = LoggerFactory.getLogger( AddPersonaCommand.class );
    private Persona _persona;
    private PersonaDao _dao;

    public AddPersonaCommand( Persona persona, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddPersonaCommand.ctor: parameter {%s}",
                persona.toString() ) );
        setHandler(handler);
        _persona = persona;
        _dao = DaoFactory.createPersonaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddPersonaCommand.ctor: attribute {%s}",
                _persona.toString() ) );
        //endregion
    }

    public AddPersonaCommand( Persona persona )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddPersonaCommand.ctor: parameter {%s}",
                persona.toString() ) );
        _persona = persona;
        setHandler(new DBHandler());
        _dao = DaoFactory.createPersonaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddPersonaCommand.ctor: attribute {%s}",
                _persona.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddPersonaCommand.execute" );
        //endregion

        _persona = _dao.insert( _persona);

        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddPersonaCommand.execute" );
        //endregion
    }

    @Override
    public Persona getReturnParam()
    {
        return _persona;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}