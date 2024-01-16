package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.ZonaDeSeguridad;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

    public List<ZonaDeSeguridad> getZonasSeguridadByMonitoreoId(Long monitoreoId) {

        List<ZonaDeSeguridad> result = new ArrayList<>();  // Initialize empty list
        _logger.debug(String.format("Get in getZonasSeguridadByMonitoreoId: monitoreoId {%d}", monitoreoId));

        try {
            CriteriaQuery<ZonaDeSeguridad> query = _builder.createQuery(ZonaDeSeguridad.class);
            Root<ZonaDeSeguridad> root = query.from(ZonaDeSeguridad.class);

            // Join with Monitoreo entity and restrict by monitoreoId
            Join<ZonaDeSeguridad, MonitoreoElectronico> monitoreoJoin = root.join("monitoreo");  // Assuming relationship name is "monitoreo"
            query.where(_builder.equal(monitoreoJoin.get("_id"), monitoreoId));

            query.select(root);
            result = _em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            _logger.warn(String.format("No zonas de seguridad found for monitoreoId: {%d}", monitoreoId));
        } catch (Exception e) {
            _logger.error(String.format("Error retrieving zonas de seguridad: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }
        //region Instrumentation
        _logger.debug(String.format("Leaving getZonasSeguridadByMonitoreoId: result list {%s}", result));
        //endregion

        return result;
    }
}
