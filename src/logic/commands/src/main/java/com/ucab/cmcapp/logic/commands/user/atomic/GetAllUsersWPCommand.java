package com.ucab.cmcapp.logic.commands.user.atomic;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersWPCommand extends Command<User> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllUsersWPCommand.class );
    private List <User> _result;
    private UserDao _dao;

    public GetAllUsersWPCommand(DBHandler handler)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllUsersWPCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createUserDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllUsersWPCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllUsersWPCommand.execute" );
        //endregion
        _result = _dao.findAll(User.class);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllUsersWPCommand.execute" );
        //endregion
    }

    @Override
    public List<User> getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
