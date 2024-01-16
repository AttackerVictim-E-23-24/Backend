package com.ucab.cmcapp.logic.commands.zona_seguridad.atomic;

import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.ZonaDeSeguridadDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelZonaDeSeguridadCommand extends Command<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( DelZonaDeSeguridadCommand.class );
    private ZonaDeSeguridad _zonaDeSeguridad;
    private ZonaDeSeguridadDao _dao;

    public DelZonaDeSeguridadCommand( ZonaDeSeguridad zonaDeSeguridad, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in DelZonaDeSeguridadCommand.ctor: parameter {%s}",
                zonaDeSeguridad.toString() ) );
        setHandler(handler);
        _zonaDeSeguridad = zonaDeSeguridad;
        _dao = DaoFactory.createZonaDeSeguridadDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving DelZonaDeSeguridadCommand.ctor: attribute {%s}",
                _zonaDeSeguridad.toString() ) );
        //endregion
    }

    public DelZonaDeSeguridadCommand( ZonaDeSeguridad zonaDeSeguridad )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in DelZonaDeSeguridadCommand.ctor: parameter {%s}",
                zonaDeSeguridad.toString() ) );
        _zonaDeSeguridad = zonaDeSeguridad;
        setHandler(new DBHandler());
        _dao = DaoFactory.createZonaDeSeguridadDao(getHandler());


        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leaving DelZonaDeSeguridadCommand.ctor: attribute {%s}",
                _zonaDeSeguridad.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in  DelZonaDeSeguridadCommand.execute" );
        //endregion

        _zonaDeSeguridad = _dao.update( _zonaDeSeguridad);

        //region Instrumentation DEBUG
        _logger.debug( "Get in  DelZonaDeSeguridadCommand.execute" );
        //endregion
    }

    @Override
    public ZonaDeSeguridad getReturnParam()
    {
        return _zonaDeSeguridad;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
