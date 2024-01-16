package com.ucab.cmcapp.logic.commands.zona_seguridad.composite;

import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetAllZonasBUserCommand;
import com.ucab.cmcapp.logic.commands.zona_seguridad.atomic.GetAllZonasWPCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllZonasByUsernameCommand extends Command<ZonaDeSeguridad> {
    private static Logger _logger = LoggerFactory.getLogger( GetAllZonasByUsernameCommand.class );
    private List<ZonaDeSeguridad> _zonas;
    private long _idMonitoreo;

    public GetAllZonasByUsernameCommand(long idMonitoreo)
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetAllZonasByUsernameCommand.ctor: parameter {%s}");
        setHandler(new DBHandler());

        _idMonitoreo = idMonitoreo;

        //region Instrumentation DEBUG
        _logger.debug( "Leaving GetAllZonasByUsernameCommand.ctor: attribute {%s}" );
        //endregion
    }

    @Override
    public void execute()
    {
        try
        {
            GetAllZonasBUserCommand getAllZonasBUserCommand = CommandFactory.createGetAllZonasBUserCommand(getHandler(), _idMonitoreo);
            getAllZonasBUserCommand.execute();
            _zonas = getAllZonasBUserCommand.getReturnParam();
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
