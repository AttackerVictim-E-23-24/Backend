package com.ucab.cmcapp.logic.commands.mailcode.atomic;

import com.ucab.cmcapp.common.entities.MailCodes;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MailCodeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetMailCodeByCodeCommand extends Command<MailCodes> {

    private static Logger _logger = LoggerFactory.getLogger( GetMailCodeByCodeCommand.class );
    private MailCodes _mailCode;
    private MailCodeDao _dao;

    public GetMailCodeByCodeCommand(MailCodes mailCodes )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetMailCodeByCodeCommand.ctor: parameter {%s}", mailCodes.toString() ) );
        _mailCode = mailCodes;
        setHandler(new DBHandler());
        _dao = DaoFactory.createMailCodeDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetMailCodeByCodeCommand.ctor: atribute {%s}", _mailCode.toString() ) );
        //endregion
    }

    public GetMailCodeByCodeCommand(MailCodes mailCodes, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetMailCodeByCodeCommand.ctor: parameter {%s}", mailCodes.toString() ) );
        _mailCode = mailCodes;
        setHandler(handler);
        _dao = DaoFactory.createMailCodeDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetMailCodeByCodeCommand.ctor: atribute {%s}", _mailCode.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetCodeByCodeCommand.execute" );
        //endregion
        _mailCode = _dao.getCodeByCode(_mailCode.getCode());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetCodeByCodeCommand.execute" );
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
