package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.MailCodes;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MailCodeDao extends BaseDao<MailCodes> {

    private static Logger _logger = LoggerFactory.getLogger( MailCodeDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;
    public MailCodeDao() {
        super();
    }

    public MailCodeDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();

    }

    public MailCodes getCodeByCode(int code )
    {
        MailCodes result = EntityFactory.createMailCodes();
        _logger.debug( String.format( "Get in MailCodeDao.getCodeByCode: parameter {%s}", code ) );
        try
        {
            CriteriaQuery<MailCodes> query = _builder.createQuery( MailCodes.class );
            Root<MailCodes> root = query.from( MailCodes.class );

            query.select( root );
            query.where( _builder.equal( root.get( "code" ), code ) );


            result = _em.createQuery( query ).getSingleResult();
        }
        catch ( NoResultException e )
        {
            _logger.error( String.format( "Error MailCodeDao.getCodeByCode: No Result {%s}", e.getMessage() ) );
        }
        catch ( Exception e )
        {
            _logger.error( String.format( "Error MailCodeDao.getCodeByCode: {%s}", e.getMessage() ) );
            throw new CupraException( e.getMessage() );
        }
        //region Instrumentation
        _logger.debug( String.format( "Leavin MailCodeDao.getCodeByCode: result {%s}", result ) );
        //endregion

        return result;
    }
}
