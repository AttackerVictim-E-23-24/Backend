package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public class ZonaDeSeguridadDao extends BaseDao<ZonaDeSeguridad> {

    private static Logger _logger = LoggerFactory.getLogger( ZonaDeSeguridadDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;
    public ZonaDeSeguridadDao() {
        super();
    }

    public ZonaDeSeguridadDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();

    }
}
