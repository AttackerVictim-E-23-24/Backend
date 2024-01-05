package com.ucab.cmcapp.logic.commands.coordenada.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.PutCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.persona.atomic.AddPersonaCommand;
import com.ucab.cmcapp.logic.commands.persona.composite.CreatePersonaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateCoordenadaCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( UpdateCoordenadaCommand.class );
    private Coordenada _coordenada;
    private Coordenada _result;
    private PutCoordenadaCommand _putCoordenadaCommand;

    public UpdateCoordenadaCommand(Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateCoordenadaCommand.ctor");
        //endregion

        _coordenada = coordenada;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving UpdateCoordenadaCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering UpdateCoordenadaCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _putCoordenadaCommand = CommandFactory.createPutCoordenadaCommand(_coordenada, getHandler());
            _putCoordenadaCommand.execute();
            _result = _putCoordenadaCommand.getReturnParam();
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
        _logger.debug( "Leaving UpdateCoordenadaCommand.execute");
        //endregion
    }

    @Override
    public Coordenada getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
