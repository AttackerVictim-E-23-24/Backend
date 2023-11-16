package com.ucab.cmcapp.persistence;

import com.ucab.cmcapp.common.exceptions.DbHandlerException;
import com.ucab.cmcapp.properties.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class DBHandler
{
    private static Logger _logger = LoggerFactory.getLogger( DBHandler.class );
    private static EntityManagerFactory _emf;

    private EntityManager _em;
    private EntityTransaction _tx;
    private boolean _isTransaction;

    static
    {
        getEntityManagerFactory();
    }


    /**
     * Name: getSession
     * Description: Metodo que retorna una sesion de base de datos
     *
     * @return Sesion de base de datos
     */
    public EntityManager getSession()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in to DbHandler.getSession" );
        //endregion

        try
        {
            if ( _em == null )
            {
                _em = _emf.createEntityManager();
            }
        }
        catch ( Exception e )
        {
            //region Instrumentation DEBUG
            _logger.debug( "Exception in DbHandler.getSession : {}", e.getMessage() );
            //endregion
            throw new DbHandlerException(e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DbHandler.getSession: EntityManager {}", _em );
        //endregion

        return _em;
    }

    /**
     * Name: closeSession
     * Description: Metodo que cierra la sesion de base de datos
     */
    public void closeSession()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in to DbHandler.closeSession" );
        //endregion

        try
        {
            if ( _em != null )
            {
                _em.close();
                _em = null;
            }
        }
        catch ( Exception e )
        {
            //region Instrumentation DEBUG
            _logger.debug( "Exception in DbHandler.closeSession : {}", e.getMessage() );
            //endregion
            throw new DbHandlerException(e.getMessage() );
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DbHandler.closeSession" );
        //endregion
    }

    /**
     * Name: beginTransaction
     * Description: Metodo que inicia una transaccion en la sesion de base de datos
     */
    public void beginTransaction()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in to DbHandler.beginTransaction" );
        //endregion

        if ( _em == null )
        {
            getSession();
        }

        if ( _tx == null )
        {
            _tx = _em.getTransaction();
        }

        if ( !_tx.isActive() )
        {
            _tx.begin();
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DbHandler.beginTransaction: EntityTransaction {}", _tx );
        //endregion

    }

    /**
     * Name: finishTransaction
     * Description: Metodo que confirma los cambios (cerrando) de una transaccion abierta
     */
    public void finishTransaction()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in to DbHandler.finishTransaction" );
        //endregion

        if ( _tx != null && _tx.isActive() )
        {
            _tx.commit();
            _tx = null;
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leavin DbHandler.finishTransaction" );
        //endregion
    }

    /**
     * Name: rollbackTransaction
     * Description: Metodo que revierte los cambios efectuados en la transaccion
     */
    public void rollbackTransaction()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Get in to DbHandler.rollbackTransaction" );
        //endregion

        if ( _tx != null && _tx.isActive() )
        {
            _tx.rollback();
            _tx = null;
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leavin DbHandler.rollbackTransaction" );
        //endregion
    }

    /**
     * @return
     */
    public boolean isTransaction()
    {
        return _isTransaction;
    }

    /**
     * @param transaction
     */
    public void setTransaction( boolean transaction )
    {
        _isTransaction = transaction;
    }


    private static void getEntityManagerFactory()
    {
        //region Instrumentation DEBUG
        _logger.debug( "Getting in DBHandler.getEntityManagerFactory" );
        //endregion

        if ( _emf == null )
        {
            try
            {
                _emf = Persistence.createEntityManagerFactory(Registry.getInstance().getProperty(Registry.DB_UNIT));
            }
            catch ( Exception e )
            {
                //region Instrumentation DEBUG
                _logger.debug( "Exception in DbHandler.getEntityManagerFactory : {}", e.getMessage() );
                //endregion
                throw new DbHandlerException(e.getMessage() );
            }
        }

        //region Instrumentation DEBUG
        _logger.debug( "Leaving DBHandler.getEntityManagerFactory" );
        //endregion
    }

}

