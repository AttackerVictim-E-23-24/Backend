package com.ucab.cmcapp.persistence.dao;
import com.ucab.cmcapp.common.entities.*;
import com.ucab.cmcapp.persistence.DBHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

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


}
