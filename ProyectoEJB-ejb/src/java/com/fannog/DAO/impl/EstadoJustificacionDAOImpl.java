package com.fannog.DAO.impl;

import com.fannog.entities.EstadoJustificacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.EstadoJustificacionDAO;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class EstadoJustificacionDAOImpl implements EstadoJustificacionDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public EstadoJustificacion create(EstadoJustificacion estadoJustificacion) throws ServicioException {
        try {
            EstadoJustificacion yaExiste = (EstadoJustificacion) em.createNamedQuery("EstadoJustificacion.findByNombre").setParameter("nombre", estadoJustificacion.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un estado de justificacion con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(estadoJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el estado de justificacion");
        }

        return estadoJustificacion;
    }

    @Override
    public EstadoJustificacion edit(EstadoJustificacion estadoJustificacion) throws ServicioException {
        try {
            em.merge(estadoJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el estado de justificacion");
        }

        return estadoJustificacion;
    }

    @Override
    public void remove(EstadoJustificacion estadoJustificacion) throws ServicioException {
        try {
            em.remove(estadoJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el estado de justificacion");
        }
    }

    @Override
    public EstadoJustificacion findById(Long id) {
        EstadoJustificacion estadoJustificacion = em.find(EstadoJustificacion.class, id);

        return estadoJustificacion;
    }

    @Override
    public List<EstadoJustificacion> findAll() {
        List<EstadoJustificacion> estadosJustificacion = em.createNamedQuery("EstadoJustificacion.findAll").getResultList();

        return estadosJustificacion;
    }

}
