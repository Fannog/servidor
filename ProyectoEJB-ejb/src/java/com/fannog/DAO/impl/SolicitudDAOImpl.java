package com.fannog.DAO.impl;

import com.fannog.entities.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.SolicitudDAO;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class SolicitudDAOImpl implements SolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Solicitud create(Solicitud solicitud) throws ServicioException {
        try {
            em.persist(solicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear la solicitud");
        }

        return solicitud;
    }

    @Override
    public Solicitud edit(Solicitud solicitud) throws ServicioException {
        try {
            em.merge(solicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la solicitud");
        }

        return solicitud;
    }

    @Override
    public void remove(Solicitud solicitud) throws ServicioException {
        try {
            em.remove(solicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la solicitud");
        }
    }

    @Override
    public Solicitud findById(Long id) {
        Solicitud solicitud = em.find(Solicitud.class, id);

        return solicitud;
    }

    @Override
    public List<Solicitud> findAll() {
        List<Solicitud> solicitudes = em.createNamedQuery("Solicitud.findAll").getResultList();

        return solicitudes;
    }

}
