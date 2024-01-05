package com.ucab.cmcapp.logic.commands.coordenada.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.AddCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.AddZonaDeSeguridadCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.CreateZonaDeSeguridadCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCoordenadaCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( CreateCoordenadaCommand.class );
    private Coordenada _coordenada;
    private Coordenada _result;
    private AddCoordenadaCommand _addCoordenadaCommand;

    public CreateCoordenadaCommand(Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateCoordenadaCommand.ctor");
        //endregion

        _coordenada = coordenada;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateCoordenadaCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateCoordenadaCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addCoordenadaCommand = CommandFactory.createAddCoordenadaCommand(_coordenada, getHandler());
            _addCoordenadaCommand.execute();
            _result = _addCoordenadaCommand.getReturnParam();
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
        _logger.debug( "Leaving CreateCoordenadaCommand.execute");
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
