package com.ucab.cmcapp.logic.commands.user.atomic;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserByUsernameCommand extends Command<User> {

    private static Logger _logger = LoggerFactory.getLogger( GetUserByUsernameCommand.class );
    private User _user;
    private UserDao _dao;

    public GetUserByUsernameCommand(User user )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetUserByUsernameCommand.ctor: parameter {%s}", user.toString() ) );
        _user = user;
        setHandler(new DBHandler());
        _dao = DaoFactory.createUserDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetUserByUsernameCommand.ctor: atribute {%s}", _user.toString() ) );
        //endregion
    }

    public GetUserByUsernameCommand(User user, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetUserByUsernameCommand.ctor: parameter {%s}", user.toString() ) );
        _user = user;
        setHandler(handler);
        _dao = DaoFactory.createUserDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetUserByUsernameCommand.ctor: atribute {%s}", _user.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetUserByUsernameCommand.execute" );
        //endregion
        _user = _dao.getUserByUsername(_user.getUserName());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetUserByUsernameCommand.execute" );
        //endregion
    }

    @Override
    public User getReturnParam()
    {
        return _user;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
