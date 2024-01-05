package com.ucab.cmcapp.logic.commands.user.composite;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.atomic.GetAllUsersWPCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByIdCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersCommand extends Command<User> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllUsersCommand.class );
    private List <User> _users;

    public GetAllUsersCommand()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllUsersCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllUsersCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllUsersWPCommand getAllUsersWPCommand = CommandFactory.createGetAllUsersWPCommand(getHandler());
            getAllUsersWPCommand.execute();
            _users = getAllUsersWPCommand.getReturnParam();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
    }

    @Override
    public List <User> getReturnParam()
    {
        return _users;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
