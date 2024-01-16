package com.ucab.cmcapp.logic.commands.user.atomic;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.persona.atomic.PutPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutUserCommand extends Command<User> {

    private static Logger _logger = LoggerFactory.getLogger(PutUserCommand.class );
    private User _user;
    private UserDao _dao;

    public PutUserCommand( User user, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutUserCommand.ctor: parameter {%s}",
                user.toString() ) );
        setHandler(handler);
        _user = user;
        _dao = DaoFactory.createUserDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutUserCommand.ctor: attribute {%s}",
                _user.toString() ) );
        //endregion
    }

    public PutUserCommand( User user)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutUserCommand.ctor: parameter {%s}",
                user.toString() ) );
        _user = user;
        setHandler(new DBHandler());
        _dao = DaoFactory.createUserDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutUserCommand.ctor: attribute {%s}",
                _user.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  PutUserCommand.execute" );
        //endregion

        _user = _dao.update( _user);

        //region Instrumentation DEBUG
        _logger.debug( "Leaving  PutUserCommand.execute" );
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
