package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.entities.HistoricoMovimiento;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoricoMovimientoDao extends BaseDao<HistoricoMovimiento> {

    private static Logger _logger = LoggerFactory.getLogger( HistoricoMovimientoDao.class );
    private EntityManager _em;
    private CriteriaBuilder _builder;

    public HistoricoMovimientoDao()
    {
        super();
    }

    public HistoricoMovimientoDao( DBHandler handler )
    {
        super( handler );

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();
    }

    public List<HistoricoMovimiento> getHistoricoMovimientoByUserId(Long userId) {

        List<HistoricoMovimiento> result = new ArrayList<>();  // Initialize empty list
        _logger.debug(String.format("Get in getHistoricoMovimientoByUserId: userId {%d}", userId));

        try {
            CriteriaQuery<HistoricoMovimiento> query = _builder.createQuery(HistoricoMovimiento.class);
            Root<HistoricoMovimiento> root = query.from(HistoricoMovimiento.class);

            // Join with User entity and restrict by userId
            Join<HistoricoMovimiento, User> userJoin = root.join("usuario");  // Assuming relationship name is "usuario"
            query.where(_builder.equal(userJoin.get("_id"), userId));

            query.select(root);
            result = _em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            _logger.warn(String.format("No historico movimiento found for userId: {%d}", userId));
        } catch (Exception e) {
            _logger.error(String.format("Error retrieving historico movimiento: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }
        //region Instrumentation
        _logger.debug(String.format("Leaving getHistoricoMovimientoByUserId: result list {%s}", result));
        //endregion

        return result;
    }

    public List<HistoricoMovimiento> getHistoricoMovimientoBySeconds(Long userId, int seconds) {

        List<HistoricoMovimiento> result = new ArrayList<>();
        _logger.debug(String.format("Get in getHistoricoMovimientoBySeconds: userId {%d}, seconds {%d}", userId, seconds));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<HistoricoMovimiento> query = builder.createQuery(HistoricoMovimiento.class);
            Root<HistoricoMovimiento> root = query.from(HistoricoMovimiento.class);

            // Join with User entity and restrict by userId
            Join<HistoricoMovimiento, User> userJoin = root.join("usuario");

            // Use the correct field name for fecha
            // Assuming the date field is named "fecha" in HistoricoMovimiento
            Predicate fechaPredicate = builder.greaterThan(root.get("_fecha"), builder.literal(getThresholdDate(seconds)));

            query.where(
                    builder.equal(userJoin.get("_id"), userId),
                    fechaPredicate
            );

            query.select(root);
            result = _em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            _logger.warn(String.format("No historico movimiento found for userId: {%d}", userId));
        } catch (Exception e) {
            _logger.error(String.format("Error retrieving historico movimiento: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        //region Instrumentation
        _logger.debug(String.format("Leaving getHistoricoMovimientoBySeconds: result list {%s}", result));
        //endregion

        return result;
    }

    private Date getThresholdDate(int seconds) {
        // Obtén la fecha actual y resta los segundos especificados
        long thresholdMillis = System.currentTimeMillis() - (seconds * 1000);
        return new Date(thresholdMillis);
    }
}
