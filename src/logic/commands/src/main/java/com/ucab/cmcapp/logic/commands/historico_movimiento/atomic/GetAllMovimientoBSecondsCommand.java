package com.ucab.cmcapp.logic.commands.historico_movimiento.atomic;

import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.HistoricoMovimientoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllMovimientoBSecondsCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllMovimientoBSecondsCommand.class );
    private List<HistoricoMovimiento> _result;
    private HistoricoMovimientoDao _dao;

    private long _idUsuario;

    private int _segundos;

    public GetAllMovimientoBSecondsCommand(DBHandler handler, long idUsuario, int segundos)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllMovimientoBSecondsCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createHistoricoMovimientoDao(getHandler());
        _idUsuario = idUsuario;
        _segundos = segundos;

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllMovimientoBSecondsCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllMovimientoBSecondsCommand.execute" );
        //endregion
        _result = _dao.getHistoricoMovimientoBySeconds(_idUsuario, _segundos);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllMovimientoBSecondsCommand.execute" );
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
