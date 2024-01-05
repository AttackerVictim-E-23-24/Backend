package com.ucab.cmcapp.logic.commands.zona_seguridad.composite;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.atomic.GetUserByIdCommand;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetZonaByIdCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetZonaCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( GetZonaCommand.class );
    private ZonaDeSeguridad _zona;
    long _id;

    public GetZonaCommand( ZonaDeSeguridad zonaDeSeguridad )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetZonaCommand.ctor: parameter {%s}",
                zonaDeSeguridad.toString() ) );
        _id = zonaDeSeguridad.get_id();
        _zona = zonaDeSeguridad;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving GetZonaCommand.ctor: attribute {%s}",
                _zona.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetZonaByIdCommand getZonaByIdCommand = CommandFactory.createGetZonaByIdCommand(getHandler(), _id);
            getZonaByIdCommand.execute();
            _zona = getZonaByIdCommand.getReturnParam();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
    }

    @Override
    public ZonaDeSeguridad getReturnParam()
    {
        return _zona;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
