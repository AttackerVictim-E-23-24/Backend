package com.ucab.cmcapp.logic.commands.zona_seguridad.composite;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.user.atomic.AddUserCommand;
import com.ucab.cmcapp.logic.commands.user.composite.CreateUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.DelZonaDeSeguridadCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteZonaDeSeguridadCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( DeleteZonaDeSeguridadCommand.class );
    private ZonaDeSeguridad _zona;
    private ZonaDeSeguridad _result;
    private DelZonaDeSeguridadCommand _DelZonaDeSeguridadCommand;

    public DeleteZonaDeSeguridadCommand(ZonaDeSeguridad zonaDeSeguridad )
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering DeleteZonaDeSeguridadCommand.ctor");
        //endregion

        _zona = zonaDeSeguridad;
        setHandler(new DBHandler());

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DeleteZonaDeSeguridadCommand.ctor");
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Entering DeleteZonaDeSeguridadCommand.execute");
        //endregion

        try
        {
            getHandler().beginTransaction();
            _DelZonaDeSeguridadCommand = CommandFactory.createDelZonaDeSeguridadCommand(_zona, getHandler());
            _DelZonaDeSeguridadCommand.execute();
            _result = _DelZonaDeSeguridadCommand.getReturnParam();
            getHandler().finishTransaction();
            getHandler().closeSession();
        }
        catch (Exception e)
        {
            getHandler().rollbackTransaction();
            getHandler().closeSession();
            throw e;
        }
        //region Instrumentation DEBUG
        _logger.debug( "Leaving DeleteZonaDeSeguridadCommand.execute");
        //endregion
    }

    @Override
    public ZonaDeSeguridad getReturnParam()
    {
        return _result;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
