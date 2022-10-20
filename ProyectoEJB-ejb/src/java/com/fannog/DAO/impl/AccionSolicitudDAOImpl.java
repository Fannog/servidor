package com.fannog.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.AccionSolicitudDAO;
import com.fannog.entities.AccionSolicitud;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class AccionSolicitudDAOImpl implements AccionSolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public AccionSolicitud create(AccionSolicitud accionSolicitud) throws ServicioException {
        try {
            em.persist(accionSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar registrar la accion");
        }

        return accionSolicitud;
    }

    @Override
    public AccionSolicitud edit(AccionSolicitud accionSolicitud) throws ServicioException {
        try {
            em.merge(accionSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la acción");
        }

        return accionSolicitud;
    }

    @Override
    public void remove(AccionSolicitud accionSolicitud) throws ServicioException {
        try {
            em.remove(accionSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la acción");
        }
    }

    @Override
    public AccionSolicitud findById(Long id) {
        AccionSolicitud accionSolicitud = em.find(AccionSolicitud.class, id);

        return accionSolicitud;
    }

    @Override
    public List<AccionSolicitud> findAll() {
        List<AccionSolicitud> accionesSolicitud = em.createNamedQuery("AccionSolicitud.findAll").getResultList();

        return accionesSolicitud;
    }

}
