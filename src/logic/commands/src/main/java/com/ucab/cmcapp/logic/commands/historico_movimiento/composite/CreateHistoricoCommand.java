package com.ucab.cmcapp.logic.commands.historico_movimiento.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.AddCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.coordenada.composite.CreateCoordenadaCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.AddHistoricoCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateHistoricoCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( CreateHistoricoCommand.class );
    private HistoricoMovimiento _historico;
    private HistoricoMovimiento _result;
    private AddHistoricoCommand _addHistoricoCommand;

    public CreateHistoricoCommand(HistoricoMovimiento historico)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateHistoricoCommand.ctor");
        //endregion

        _historico = historico;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving CreateHistoricoCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering CreateHistoricoCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _addHistoricoCommand = CommandFactory.createAddHistoricoCommand(_historico, getHandler());
            _addHistoricoCommand.execute();
            _result = _addHistoricoCommand.getReturnParam();
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
        _logger.debug( "Leaving CreateHistoricoCommand.execute");
        //endregion
    }

    @Override
    public HistoricoMovimiento getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }


}
