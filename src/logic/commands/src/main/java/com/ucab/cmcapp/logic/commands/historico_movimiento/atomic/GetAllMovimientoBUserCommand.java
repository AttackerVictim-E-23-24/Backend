package com.ucab.cmcapp.logic.commands.historico_movimiento.atomic;

import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetAllZonasWPCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.HistoricoMovimientoDao;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllMovimientoBUserCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllMovimientoBUserCommand.class );
    private List<HistoricoMovimiento> _result;
    private HistoricoMovimientoDao _dao;

    private long _idUsuario;

    public GetAllMovimientoBUserCommand(DBHandler handler, long idUsuario)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllMovimientoBUserCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createHistoricoMovimientoDao(getHandler());
        _idUsuario = idUsuario;

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllMovimientoBUserCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllMovimientoBUserCommand.execute" );
        //endregion
        _result = _dao.getHistoricoMovimientoByUserId(_idUsuario);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllMovimientoBUserCommand.execute" );
        //endregion
    }

    @Override
    public List<HistoricoMovimiento> getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
