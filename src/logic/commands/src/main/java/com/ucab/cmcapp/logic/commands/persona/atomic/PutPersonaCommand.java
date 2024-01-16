package com.ucab.cmcapp.logic.commands.persona.atomic;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.PutMonitoreoCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import com.ucab.cmcapp.persistence.dao.PersonaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutPersonaCommand extends Command<Persona> {

    private static Logger _logger = LoggerFactory.getLogger(PutPersonaCommand.class );
    private Persona _persona;
    private PersonaDao _dao;

    public PutPersonaCommand( Persona persona, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutPersonaCommand.ctor: parameter {%s}",
                persona.toString() ) );
        setHandler(handler);
        _persona = persona;
        _dao = DaoFactory.createPersonaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutPersonaCommand.ctor: attribute {%s}",
                _persona.toString() ) );
        //endregion
    }

    public PutPersonaCommand( Persona persona)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in PutPersonaCommand.ctor: parameter {%s}",
                persona.toString() ) );
        _persona = persona;
        setHandler(new DBHandler());
        _dao = DaoFactory.createPersonaDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving PutPersonaCommand.ctor: attribute {%s}",
                _persona.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  PutPersonaCommand.execute" );
        //endregion

        _persona = _dao.update( _persona );

        //region Instrumentation DEBUG
        _logger.debug( "Leaving  PutPersonaCommand.execute" );
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
