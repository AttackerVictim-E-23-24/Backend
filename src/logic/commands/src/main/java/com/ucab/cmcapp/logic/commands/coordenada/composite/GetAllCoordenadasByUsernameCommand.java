package com.ucab.cmcapp.logic.commands.coordenada.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.GetAllCoordenadasBUserCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.atomic.GetAllMovimientoBUserCommand;
import com.ucab.cmcapp.logic.commands.historico_movimiento.composite.GetAllMovimientoByUsernameCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllCoordenadasByUsernameCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger(GetAllCoordenadasByUsernameCommand.class );
    private List<Coordenada> _coordenadas;
    private long _idUsuario;

    public GetAllCoordenadasByUsernameCommand(long idUsuario)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllCoordenadasByUsernameCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        _idUsuario = idUsuario;

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllCoordenadasByUsernameCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllCoordenadasBUserCommand getAllCoordenadasBUserCommand = CommandFactory.createGetAllCoordenadasBUserCommand(getHandler(), _idUsuario);
            getAllCoordenadasBUserCommand.execute();
            _coordenadas = getAllCoordenadasBUserCommand.getReturnParam();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
    }

    @Override
    public List <Coordenada> getReturnParam()
    {
        return _coordenadas;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
