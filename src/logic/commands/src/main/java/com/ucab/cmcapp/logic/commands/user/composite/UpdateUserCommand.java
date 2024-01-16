package com.ucab.cmcapp.logic.commands.user.composite;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.persona.atomic.PutPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.UpdatePersonaCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.PutUserCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateUserCommand extends Command<User> {

    private static Logger _logger = LoggerFactory.getLogger( UpdateUserCommand.class );
    private User _user;
    private User _result;
    private PutUserCommand _putUserCommand;

    public UpdateUserCommand( User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateUserCommand.ctor");
        //endregion

        _user = user;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UpdateUserCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateUserCommand.execute ");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _putUserCommand = CommandFactory.createPutUserCommand(_user, getHandler());
            _putUserCommand.execute();
            _result = _putUserCommand.getReturnParam();
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
        _logger.debug( "Leaving UpdateUserCommand.execute");
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }



}
