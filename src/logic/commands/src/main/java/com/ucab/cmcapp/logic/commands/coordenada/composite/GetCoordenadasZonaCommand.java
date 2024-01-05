package com.ucab.cmcapp.logic.commands.coordenada.composite;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.coordenada.atomic.GetCoordenadasZonasByIdCommand;
import com.ucab.cmcapp.logic.commands.user.atomic.GetAllUsersWPCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetCoordenadasZonaCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( GetCoordenadasZonaCommand.class );
    private List<Coordenada> _coordenadas;

    private long _idZona;

    public GetCoordenadasZonaCommand(long idZona)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetCoordenadasZonaCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        _idZona = idZona;

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetCoordenadasZonaCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetCoordenadasZonasByIdCommand getCoordenadasZonasByIdCommand = CommandFactory.createGetCoordenadasZonasByIdCommand(getHandler(), _idZona);
            getCoordenadasZonasByIdCommand.execute();
            _coordenadas = getCoordenadasZonasByIdCommand.getReturnParam();
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
