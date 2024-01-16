package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBUserCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.HistoricoMovimientoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllCoordenadasBUserCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllCoordenadasBUserCommand.class );
    private List<Coordenada> _result;
    private CoordenadaDao _dao;

    private long _idUsuario;

    public GetAllCoordenadasBUserCommand(DBHandler handler, long idUsuario)
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetAllCoordenadasBUserCommand.ctor: parameter {%s}", "getAll" ) );
        //endregion

        setHandler(handler);
        _dao = DaoFactory.createCoordenadaDao(getHandler());
        _idUsuario = idUsuario;

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetAllCoordenadasBUserCommand.ctor: attribute {%s}", "getAll" ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  GetAllCoordenadasBUserCommand.execute" );
        //endregion
        _result = _dao.getAllCoordenadasByUserId(_idUsuario);
        //region Instrumentation DEBUG
        _logger.debug( "Leaving  GetAllCoordenadasBUserCommand.execute" );
        //endregion
    }

    @Override
    public List<Coordenada> getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
