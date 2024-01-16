package com.ucab.cmcapp.logic.commands.historico_movimiento.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.AddCoordenadaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.HistoricoMovimientoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddHistoricoCommand extends Command<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( AddHistoricoCommand.class );
    private HistoricoMovimiento _historico;
    private HistoricoMovimientoDao _dao;

    public AddHistoricoCommand( HistoricoMovimiento historico, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddHistoricoCommand.ctor: parameter {%s}",
                historico.toString() ) );
        setHandler(handler);
        _historico = historico;
        _dao = DaoFactory.createHistoricoMovimientoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddHistoricoCommand.ctor: attribute {%s}",
                _historico.toString() ) );
        //endregion
    }

    public AddHistoricoCommand( HistoricoMovimiento historico )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in AddHistoricoCommand.ctor: parameter {%s}",
                historico.toString() ) );
        _historico = historico;
        setHandler(new DBHandler());
        _dao = DaoFactory.createHistoricoMovimientoDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving AddHistoricoCommand.ctor: attribute {%s}",
                _historico.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddHistoricoCommand.execute" );
        //endregion

        _historico = _dao.insert( _historico );

        //region Instrumentation DEBUG
        _logger.debug( "Get in  AddHistoricoCommand.execute" );
        //endregion
    }

    @Override
    public HistoricoMovimiento getReturnParam()
    {
        return _historico;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
