package com.ucab.cmcapp.logic.commands.coordenada.atomic;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.logic.commands.monitoreo_electronico.atomic.GetMonitoreoByCedulaCommand;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.CoordenadaDao;
import com.ucab.cmcapp.persistence.dao.MonitoreoElectronicoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCoordenadaByLatLonCommand extends Command<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( GetCoordenadaByLatLonCommand.class );
    private Coordenada _coordenada;
    private CoordenadaDao _dao;

    public GetCoordenadaByLatLonCommand(Coordenada coordenada )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetCoordenadaByLatLonCommand.ctor: parameter {%s}", coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(new DBHandler());
        _dao = DaoFactory.createCoordenadaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "Leavin GetCoordenadaByLatLonCommand.ctor: atribute {%s}", _coordenada.toString() ) );
        //endregion
    }

    public GetCoordenadaByLatLonCommand(Coordenada coordenada, DBHandler handler )
    {
        //region Instrumentation DEBUG
        _logger.debug( String.format( "Get in GetCoordenadaByLatLonCommand.ctor: parameter {%s}", coordenada.toString() ) );
        _coordenada = coordenada;
        setHandler(handler);
        _dao = DaoFactory.createCoordenadaDao(getHandler());

        //region Instrumentation DEBUG
        _logger.debug( String.format( "GetCoordenadaByLatLonCommand.ctor: atribute {%s}", _coordenada.toString() ) );
        //endregion
    }

    @Override
    public void execute()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in GetCoordenadaByLatLonCommand. execute" );
        //endregion
        _coordenada = _dao.getCoordenadaByLatLon(_coordenada.getLatitud(), _coordenada.getLongitud(), _coordenada.getZonaSeg().get_id());
        //region Instrumentation DEBUG
        _logger.debug( "Leavin  GetCoordenadaByLatLonCommand.execute" );
        //endregion
    }

    @Override
    public Coordenada getReturnParam()
    {
        return _coordenada;
    }

    @Override
    public void closeHandlerSession()
    {
        getHandler().closeSession();
    }
}
