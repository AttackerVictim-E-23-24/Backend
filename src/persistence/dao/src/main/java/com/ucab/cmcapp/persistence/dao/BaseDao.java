package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.exceptions.DeleteException;
import com.ucab.cmcapp.common.exceptions.FindAllException;
import com.ucab.cmcapp.common.exceptions.FindException;
import com.ucab.cmcapp.common.exceptions.InsertException;
import com.ucab.cmcapp.common.exceptions.UpdateException;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.properties.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public abstract class BaseDao<T>
{

    static final int MAX_RESULTS_PER_PAGE =
            Integer.parseInt( Registry.getInstance().getProperty( Registry.MAX_RESULTS_PER_PAGE ) );
    static private DBHandler _dbHandler;
    private static Logger _logger = LoggerFactory.getLogger( BaseDao.class );
    private EntityManager _entityManager;

    public EntityManager getEntityManager()
    {
        return _entityManager;
    }

    public void setEntityManager( EntityManager entityManager )
    {
        _entityManager = entityManager;
    }

    public BaseDao()
    {
        if ( Objects.isNull( _dbHandler ) )
        {
            _dbHandler = new DBHandler();
        }
    }

    public BaseDao( DBHandler handler )
    {
        _dbHandler = handler;
    }

    /**
     * Name:                  update
     * Description:           method for adding a record on the DB
     *
     * @param entity entity
     */
    public T insert( T entity )
    {
        setEntityManager( _dbHandler.getSession() );
        T result;

        //implementar logger

        try
        {
            getEntityManager().persist( entity );
            getEntityManager().flush();
            getEntityManager().refresh( entity );
        }
        catch ( Exception e )
        {
            //implementar logger
            throw new InsertException( e.getMessage() + "Entity: " + entity.toString() );
        }

        //implementar logger
        return entity;
    }

    /**
     * Name:                  insertMultiple
     * Description:           method for adding multiple records in the DB
     *
     * @param entityList entityList
     */
    public List<T> insertMultiple( List<T> entityList )
    {
        for ( T entity : entityList )
        {
            insert( entity );
        }
        return entityList;
    }

    /**
     * Name:                  update
     * Description:           method for updating a record on the DB
     *
     * @param entity entity
     */
    public T update( T entity )
    {
        setEntityManager( _dbHandler.getSession() );
        //implementar logger

        try
        {
            getEntityManager().merge( entity );
            getEntityManager().flush();
        }
        catch ( Exception e )
        {
            //implementar logger
            throw new UpdateException( e, entity.toString() );
        }
        //implementar logger
        return entity;
    }

    /**
     * Name:                  delete
     * Description:           method for deleting a record from the DB
     *
     * @param entity entity
     */
    public T delete( T entity )
    {
        setEntityManager( _dbHandler.getSession() );
        //implementar logger
        try
        {
            getEntityManager().remove( entity );
            getEntityManager().flush();
        }
        catch ( Exception e )
        {
            //implementar logger
            throw new DeleteException( e.getMessage() + "Entity: " + entity.toString() );
        }

        //implementar logger
        return entity;
    }

    /**
     * Name:                  findAll
     * Description:           method for collecting all registers from an entity
     *
     * @param type Class
     *
     * @author teixbr
     * @since 20/10/17
     */
    public List<T> findAll( Class<T> type )
    {
        //implementar logger
        setEntityManager( _dbHandler.getSession() );

        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery( type );
        final Root<T> root = criteriaQuery.from( type );
        final List<T> list;

        try
        {
            criteriaQuery.select( root );

            list = getEntityManager().createQuery( criteriaQuery ).getResultList();

        }
        catch ( Exception e )
        {
            //implementar logger
            throw new FindAllException( e.getMessage() + " Type" + type.toString() );
        }
        //implementar logger
        return list;
    }

    /**
     * Name:                  find
     * Description:           method used for find a single record by it's ID
     *
     * @param id   id of entity to search
     * @param type Class
     */
    public T find( Long id, Class<T> type )
    {
        //implementar logger
        T entity;

        try
        {
            setEntityManager( _dbHandler.getSession() );
            entity = getEntityManager().find( type, id );
        }
        catch ( Exception e )
        {
            //implementar logger
            throw new FindException( e.getMessage() + "Id: " + id + " Type" + type.toString() );
        }

        //implementar logger
        return entity;
    }

    /**
     * Name:                  getDBHandler
     * Description:           method that returns the daoHandler
     *
     * @return instance of daoHandler
     */
    public DBHandler getDBHandler()
    {
        return _dbHandler;
    }


}
