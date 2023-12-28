package com.ucab.cmcapp.logic.commands.persona.composite;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePersonaCommand extends Command<Persona> {

    private static Logger _logger = LoggerFactory.getLogger( CreatePersonaCommand.class );
    private Persona _persona;
    private Persona _result;
    private AddPersonaCommand _addPersonaCommand;

    public CreatePersonaCommand(Persona persona )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreatePersonaCommand.ctor");
        //endregion

        _persona = persona;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreatePersonaCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreatePersonaCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addPersonaCommand = CommandFactory.createAddPersonaCommand(_persona, getHandler());
            _addPersonaCommand.execute();
            _result = _addPersonaCommand.getReturnParam();
            getHandler().finishTransaction();
            getHandler().closeSession();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateUserCommand.execute");
        //endregion
    }

    @Override
    public Persona getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
