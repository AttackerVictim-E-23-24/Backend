package com.ucab.cmcapp.logic.commands.mailcode.atomic;

import com.ucab.cmcapp.common.entities.MailCodes;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MailCodeDao;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMailCodeCommand extends Command<MailCodes> {

    private static Logger _logger = LoggerFactory.getLogger( AddMailCodeCommand.class );
    private MailCodes _mailCode;
    private MailCodeDao _dao;

    public AddMailCodeCommand( MailCodes mailCode, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddMailCodeCommand.ctor: parameter {%s}",
                mailCode.toString() ) );
        setHandler(handler);
        _mailCode = mailCode;
        _dao = DaoFactory.createMailCodeDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddMailCodeCommand.ctor: attribute {%s}",
                _mailCode.toString() ) );
        //endregion
    }

    public AddMailCodeCommand( MailCodes mailCode )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddMailCodeCommand.ctor: parameter {%s}",
                mailCode.toString() ) );
        _mailCode = mailCode;
        setHandler(new DBHandler());
        _dao = DaoFactory.createMailCodeDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddMailCodeCommand.ctor: attribute {%s}",
                _mailCode.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddMailCodeCommand.execute" );
        //endregion

        _mailCode = _dao.insert( _mailCode );

        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddMailCodeCommand.execute" );
        //endregion
    }

    @Override
    public MailCodes getReturnParam()
    {
        return _mailCode;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
