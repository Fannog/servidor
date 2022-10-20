package com.fannog.DAO.impl;

import com.fannog.entities.EstadoSolicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.EstadoSolicitudDAO;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class EstadoSolicitudDAOImpl implements EstadoSolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public EstadoSolicitud create(EstadoSolicitud estadoSolicitud) throws ServicioException {
        try {
            EstadoSolicitud yaExiste = (EstadoSolicitud) em.createNamedQuery("EstadoSolicitud.findByNombre").setParameter("nombre", estadoSolicitud.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un estado de solicitud con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(estadoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el estado de solicitud");
        }

        return estadoSolicitud;
    }

    @Override
    public EstadoSolicitud edit(EstadoSolicitud estadoSolicitud) throws ServicioException {
        try {
            em.merge(estadoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el estado de solicitud");
        }

        return estadoSolicitud;
    }

    @Override
    public void remove(EstadoSolicitud estadoSolicitud) throws ServicioException {
        try {
            em.remove(estadoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el estado de solicitud");
        }
    }

    @Override
    public EstadoSolicitud findById(Long id) {
        EstadoSolicitud estadoSolicitud = em.find(EstadoSolicitud.class, id);

        return estadoSolicitud;
    }

    @Override
    public List<EstadoSolicitud> findAll() {
        List<EstadoSolicitud> estadosSolicitud = em.createNamedQuery("EstadoSolicitud.findAll").getResultList();

        return estadosSolicitud;
    }

}
