package com.ucab.cmcapp.logic.commands.historico_movimiento.composite;

import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetAllZonasBUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.composite.GetAllZonasByUsernameCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllMovimientoByUsernameCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllMovimientoByUsernameCommand.class );
    private List<HistoricoMovimiento> _historicos;
    private long _idUsuario;

    public GetAllMovimientoByUsernameCommand(long idUsuario)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllMovimientoByUsernameCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        _idUsuario = idUsuario;

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllMovimientoByUsernameCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllMovimientoBUserCommand getAllMovimientoBUserCommand = CommandFactory.createGetAllMovimientoBUserCommand(getHandler(), _idUsuario);
            getAllMovimientoBUserCommand.execute();
            _historicos = getAllMovimientoBUserCommand.getReturnParam();
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
