package com.ucab.cmcapp.logic.commands.persona.composite;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.PutMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.composite.UpdateMonitoreoCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.PutPersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdatePersonaCommand extends Command<Persona> {

    private static Logger _logger = LoggerFactory.getLogger( UpdatePersonaCommand.class );
    private Persona _persona;
    private Persona _result;
    private PutPersonaCommand _putPersonaCommand;

    public UpdatePersonaCommand(Persona persona )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdatePersonaCommand.ctor");
        //endregion

        _persona = persona;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UpdatePersonaCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdatePersonaCommand.execute ");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _putPersonaCommand = CommandFactory.createPutPersonaCommand(_persona, getHandler());
            _putPersonaCommand.execute();
            _result = _putPersonaCommand.getReturnParam();
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
        _logger.debug( "Leaving UpdatePersonaCommand.execute");
        //endregion
    }

    @Override
    public Persona getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
