package com.ucab.cmcapp.logic.commands.historico_movimiento.composite;

import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBSecondsCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBUserCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllMovimientoBySecondsUsernameCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllMovimientoBySecondsUsernameCommand.class );
    private List<HistoricoMovimiento> _historicos;
    private long _idUsuario;

    private int _segundos;

    public GetAllMovimientoBySecondsUsernameCommand(long idUsuario, int segundos)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllMovimientoBySecondsUsernameCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        _idUsuario = idUsuario;
        _segundos = segundos;

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllMovimientoBySecondsUsernameCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllMovimientoBSecondsCommand getAllMovimientoBSecondsCommand = CommandFactory.createGetAllMovimientoBSecondsCommand(getHandler(), _idUsuario, _segundos);
            getAllMovimientoBSecondsCommand.execute();
            _historicos = getAllMovimientoBSecondsCommand.getReturnParam();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
    }

    @Override
    public List <HistoricoMovimiento> getReturnParam()
    {
        return _historicos;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
