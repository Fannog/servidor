package com.fannog.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.AdjuntoSolicitudDAO;
import com.fannog.entities.AdjuntoSolicitud;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class AdjuntoSolicitudDAOImpl implements AdjuntoSolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public AdjuntoSolicitud create(AdjuntoSolicitud adjuntoSolicitud) throws ServicioException {
        try {
            em.persist(adjuntoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar adjuntar el archivo");
        }

        return adjuntoSolicitud;
    }

    @Override
    public AdjuntoSolicitud edit(AdjuntoSolicitud adjuntoSolicitud) throws ServicioException {
        try {
            em.merge(adjuntoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el archivo adjunto");
        }

        return adjuntoSolicitud;
    }

    @Override
    public void remove(AdjuntoSolicitud adjuntoSolicitud) throws ServicioException {
        try {
            em.remove(adjuntoSolicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el archivo adjunto");
        }
    }

    @Override
    public AdjuntoSolicitud findById(Long id) {
        AdjuntoSolicitud adjuntoSolicitud = em.find(AdjuntoSolicitud.class, id);

        return adjuntoSolicitud;
    }

    @Override
    public List<AdjuntoSolicitud> findAll() {
        List<AdjuntoSolicitud> adjuntosSolicitud = em.createNamedQuery("AdjuntoSolicitud.findAll").getResultList();

        return adjuntosSolicitud;
    }

}
