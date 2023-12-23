package com.ucab.cmcapp.logic.commands.mailcode.composite;

import com.ucab.cmcapp.common.entities.MailCodes;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.mailcode.atomic.AddMailCodeCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateMailCodeCommand extends Command<MailCodes> {

    private static Logger _logger = LoggerFactory.getLogger( CreateMailCodeCommand.class );
    private MailCodes _mailCode;
    private MailCodes _result;
    private AddMailCodeCommand _addMailCodeCommand;

    public CreateMailCodeCommand(MailCodes mailCode )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateMailCodeCommand.ctor");
        //endregion

        _mailCode = mailCode;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateMailCodeCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateMailCodeCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addMailCodeCommand = CommandFactory.createAddMailCodeCommand(_mailCode, getHandler());
            _addMailCodeCommand.execute();
            _result = _addMailCodeCommand.getReturnParam();
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
        _logger.debug( "Leaving CreateMailCodeCommand.execute");
        //endregion
    }

    @Override
    public MailCodes getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
