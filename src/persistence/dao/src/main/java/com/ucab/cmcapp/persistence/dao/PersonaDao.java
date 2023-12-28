package com.ucab.cmcapp.persistence.dao;
import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PersonaDao extends BaseDao<Persona> {

    private static Logger _logger = LoggerFactory.getLogger( PersonaDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;
    public PersonaDao() {
        super();
    }

    public PersonaDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();

    }

    public Persona getPersonaByCedula( long cedula )
    {
        Persona result = EntityFactory.createPersona();
        _logger.debug( String.format( "Get in PersonaDao.getPersonaByCedula: parameter {%s}", cedula ) );
        try
        {
            CriteriaQuery<Persona> query = _builder.createQuery( Persona.class );
            Root<Persona> root = query.from( Persona.class );

            query.select( root );
            query.where( _builder.equal( root.get( "cedula" ), cedula ) );

            result = _em.createQuery( query ).getSingleResult();
        }
        catch ( NoResultException e )
        {
            _logger.error( String.format( "Error PersonaDao.getPersonaByCedula: No Result {%s}", e.getMessage() ) );
        }
        catch ( Exception e )
        {
            _logger.error( String.format( "Error PersonaDao.getPersonaByCedula: {%s}", e.getMessage() ) );
            throw new CupraException( e.getMessage() );
        }
        //region Instrumentation
        _logger.debug( String.format( "Leavin PersonaDao.getPersonaByCedula: result {%s}", result ) );
        //endregion

        return result;
    }


}
