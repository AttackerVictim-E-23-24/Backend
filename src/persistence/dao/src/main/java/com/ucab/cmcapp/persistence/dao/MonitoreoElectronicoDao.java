package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.Persona;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MonitoreoElectronicoDao extends BaseDao<MonitoreoElectronico> {
    private static Logger _logger = LoggerFactory.getLogger( MonitoreoElectronicoDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;
    public MonitoreoElectronicoDao() {
        super();
    }

    public MonitoreoElectronicoDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();

    }

    public MonitoreoElectronico getMonitoreoByCedula(long cedulaAtacante, long cedulaVictima) {
        MonitoreoElectronico result = EntityFactory.createMonitoreoElectronico();

        _logger.debug(String.format("Get in MonitoreoDao.getMonitoreoByCedulaAAndCedulaV: parameters cedulaAtacante {%s}, cedulaVictima {%s}", cedulaAtacante, cedulaVictima));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<MonitoreoElectronico> query = builder.createQuery(MonitoreoElectronico.class);
            Root<MonitoreoElectronico> root = query.from(MonitoreoElectronico.class);

            query.select(root);

            // Create predicates for both cedulaAtacante and cedulaVictima
            Predicate cedulaAtacantePredicate = builder.equal(root.get("cedulaAtacante"), cedulaAtacante);
            Predicate cedulaVictimaPredicate = builder.equal(root.get("cedulaVictima"), cedulaVictima);

            // Combine predicates using AND
            Predicate combinedPredicate = builder.and(cedulaAtacantePredicate, cedulaVictimaPredicate);

            // Apply the combined predicate to the query's where clause
            query.where(combinedPredicate);

            result = _em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            _logger.error(String.format("Error MonitoreoDao.getMonitoreoByCedulaAAndCedulaV: No Result {%s}", e.getMessage()));
        } catch (Exception e) {
            _logger.error(String.format("Error MonitoreoDao.getMonitoreoByCedulaAAndCedulaV: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving MonitoreoDao.getMonitoreoByCedulaAAndCedulaV: result {%s}", result));

        return result;
    }

    public MonitoreoElectronico getMonitoreoByCedulaNombreUsuario(long cedula) {
        MonitoreoElectronico result = EntityFactory.createMonitoreoElectronico();

        _logger.debug(String.format("Get in MonitoreoDao.getMonitoreoByCedula: parameter cedula {%s}", cedula));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<MonitoreoElectronico> query = builder.createQuery(MonitoreoElectronico.class);
            Root<MonitoreoElectronico> root = query.from(MonitoreoElectronico.class);

            // Create predicates for cedula and isActive
            Predicate cedulaPredicate = builder.or(
                    builder.equal(root.get("cedulaAtacante"), cedula),
                    builder.equal(root.get("cedulaVictima"), cedula)
            );
            Predicate isActivePredicate = builder.equal(root.get("isActive"), true);

            // Combine predicates using AND
            query.where(builder.and(cedulaPredicate, isActivePredicate));

            query.select(root);

            result = _em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            _logger.error(String.format("Error MonitoreoDao.getMonitoreoByCedula: No Result {%s}", e.getMessage()));
        } catch (Exception e) {
            _logger.error(String.format("Error MonitoreoDao.getMonitoreoByCedula: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving MonitoreoDao.getMonitoreoByCedula: result {%s}", result));

        return result;
    }
}
