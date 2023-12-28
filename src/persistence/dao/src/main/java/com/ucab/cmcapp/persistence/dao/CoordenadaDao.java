package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public class CoordenadaDao extends BaseDao<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger( CoordenadaDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;
    public CoordenadaDao() {
        super();
    }

    public CoordenadaDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();

    }
}