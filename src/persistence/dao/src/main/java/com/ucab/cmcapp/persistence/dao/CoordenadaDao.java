package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Coordenada;
import com.ucab.cmcapp.common.entities.MonitoreoElectronico;
import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CoordenadaDao extends BaseDao<Coordenada> {

    private static Logger _logger = LoggerFactory.getLogger(CoordenadaDao.class);
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

    public Coordenada getUltimaCoordenadaByUsuarioId(long usuarioId) {
        Coordenada result = EntityFactory.createCoordenada();

        _logger.debug(String.format("Get in CoordenadaDao.getUltimaCoordenadaByUsuarioId: parameter usuarioId {%s}", usuarioId));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<Coordenada> query = builder.createQuery(Coordenada.class);
            Root<Coordenada> root = query.from(Coordenada.class);

            query.select(root);
            query.orderBy(_builder.desc(root.get("_createAt")));
            query.where(builder.equal(root.get("usuario").get("_id"), usuarioId)); // Join on usuario and match usuario_id

            result = _em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            _logger.error(String.format("Error CoordenadaDao.getUltimaCoordenadaByUsuarioId: No Result {%s}", e.getMessage()));
        } catch (Exception e) {
            _logger.error(String.format("Error CoordenadaDao.getUltimaCoordenadaByUsuarioId: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving CoordenadaDao.getUltimaCoordenadaByUsuarioId: result {%s}", result));

        return result;
    }

    public List<Coordenada> findAllByZonaId(long zonaId) {
        List<Coordenada> resultList = new ArrayList<>();

        _logger.debug(String.format("Get in CoordenadaDao.findAllByZonaId: parameter zonaId {%s}", zonaId));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<Coordenada> query = builder.createQuery(Coordenada.class);
            Root<Coordenada> root = query.from(Coordenada.class);

            query.select(root);
            query.where(builder.equal(root.get("zonaSeg").get("_id"), zonaId)); // Join on zonaDeSeguridad and match zona_id

            resultList = _em.createQuery(query).getResultList();
        } catch (NoResultException e) {
            _logger.error(String.format("Error CoordenadaDao.findAllByZonaId: No Results {%s}", e.getMessage()));
        } catch (Exception e) {
            _logger.error(String.format("Error CoordenadaDao.findAllByZonaId: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving CoordenadaDao.findAllByZonaId: result list {%s}", resultList));

        return resultList;
    }

    public Coordenada getCoordenadaByLatLon(double latitud, double longitud, long idZonaSeg) {

        Coordenada result = EntityFactory.createCoordenada();

        _logger.debug(String.format("Get in CoordenadaDao.getCoordenadaByLatLon: parameters latitud {%s}, longitud {%s}, idZonaSeg {%s}", latitud, longitud, idZonaSeg));

        try {
            CriteriaBuilder builder = _em.getCriteriaBuilder();
            CriteriaQuery<Coordenada> query = builder.createQuery(Coordenada.class);
            Root<Coordenada> root = query.from(Coordenada.class);

            query.select(root);

            // Create predicates for latitud, longitud, and zona de seguridad ID
            Predicate latitudPredicate = builder.equal(root.get("latitud"), latitud);
            Predicate longitudPredicate = builder.equal(root.get("longitud"), longitud);
            Predicate zonaSegPredicate = builder.equal(root.get("zonaSeg").get("_id"), idZonaSeg); // Join on zonaSeg and match zona_id

            // Combine all predicates using AND
            Predicate combinedPredicate = builder.and(latitudPredicate, longitudPredicate, zonaSegPredicate);

            // Apply the combined predicate to the query's where clause
            query.where(combinedPredicate);

            result = _em.createQuery(query).getSingleResult();

        } catch (NoResultException e) {
            _logger.error(String.format("Error CoordenadaDao.getCoordenadaByLatLon: No Result {%s}", e.getMessage()));
        } catch (Exception e) {
            _logger.error(String.format("Error CoordenadaDao.getCoordenadaByLatLon: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving CoordenadaDao.getCoordenadaByLatLon: result {%s}", result));

        return result;
    }

    public List<Coordenada> getAllCoordenadasByUserId(Long userId) {

        List<Coordenada> result = new ArrayList<>();  // Initialize empty list
        _logger.debug(String.format("Get in getAllCoordenadasByUserId: userId {%d}", userId));

        try {
            CriteriaQuery<Coordenada> query = _builder.createQuery(Coordenada.class);
            Root<Coordenada> root = query.from(Coordenada.class);

            // Join with User entity and restrict by userId
            Join<Coordenada, User> userJoin = root.join("usuario");  // Assuming relationship name is "usuario"
            query.where(_builder.equal(userJoin.get("_id"), userId));

            query.orderBy(_builder.desc(root.get("_createAt")));

            // Get the results as an ordered list
            result = _em.createQuery(query).getResultList();

            // Limit the results to a maximum of 10
            if (result.size() > 10) {
                result = result.subList(0, 10);
            }
        } catch (NoResultException e) {
            _logger.warn(String.format("No coordenadas found for userId: {%d}", userId));
        } catch (Exception e) {
            _logger.error(String.format("Error retrieving coordenadas: {%s}", e.getMessage()));
            throw new CupraException(e.getMessage());
        }

        _logger.debug(String.format("Leaving getAllCoordenadasByUserId: result list {%s}", result));

        return result;
    }


}