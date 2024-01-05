package com.ucab.cmcapp.logic.commands.zona_seguridad.composite;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.atomic.GetAllUsersWPCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetAllUsersCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetAllZonasWPCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllZonasCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( GetAllZonasCommand.class );
    private List<ZonaDeSeguridad> _zonas;

    public GetAllZonasCommand()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllZonasCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllZonasCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllZonasWPCommand getAllZonasWPCommand = CommandFactory.createGetAllZonasWPCommand(getHandler());
            getAllZonasWPCommand.execute();
            _zonas = getAllZonasWPCommand.getReturnParam();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
    }

    @Override
    public List <ZonaDeSeguridad> getReturnParam()
    {
        return _zonas;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
