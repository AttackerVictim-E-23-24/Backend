package com.ucab.cmcapp.logic.commands.user.atomic;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserByIdCommand extends Command<User>
{
    private static Logger _logger = LoggerFactory.getLogger( GetUserByIdCommand.class );
    private long _userId;
    private User _result;
    private UserDao _dao;

    public GetUserByIdCommand( DBHandler handler, long userId )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetUserByIdCommand.ctor: parameter {%s}", userId ) );
        //endregion

        _userId = userId;
        setHandler(handler);
        _dao = DaoFactory.createUserDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetUserByIdCommand.ctor: attribute {%s}", userId ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetUserByIdCommand.execute" );
        //endregion
        _result = _dao.find(_userId, User.class);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetUserByIdCommand.execute" );
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
