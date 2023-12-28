package com.ucab.cmcapp.logic.commands.persona.atomic;

import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByEmailCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import com.ucab.cmcapp.persistence.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetPersonaByCedulaCommand extends Command<Persona> {

    private static Logger _logger = LoggerFactory.getLogger( GetPersonaByCedulaCommand.class );
    private Persona _persona;
    private PersonaDao _dao;

    public GetPersonaByCedulaCommand(Persona persona )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetPersonaByCedulaCommand.ctor: parameter {%s}", persona.toString() ) );
        _persona = persona;
        setHandler(new DBHandler());
        _dao = DaoFactory.createPersonaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetPersonaByCedulaCommand.ctor: atribute {%s}", _persona.toString() ) );
        //endregion
    }

    public GetPersonaByCedulaCommand(Persona persona, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetPersonaByCedulaCommand.ctor: parameter {%s}", persona.toString() ) );
        _persona = persona;
        setHandler(handler);
        _dao = DaoFactory.createPersonaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetPersonaByCedulaCommand.ctor: atribute {%s}", _persona.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetPersonaByCedulaCommand.execute" );
        //endregion
        _persona = _dao.getPersonaByCedula(_persona.getCedula());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetPersonaByCedulaCommand.execute" );
        //endregion
    }

    @Override
    public Persona getReturnParam()
    {
        return _persona;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }

}
