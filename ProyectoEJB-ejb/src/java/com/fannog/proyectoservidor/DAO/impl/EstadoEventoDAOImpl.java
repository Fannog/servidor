package com.fannog.proyectoservidor.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.EstadoEventoDAO;
import com.fannog.proyectoservidor.entities.EstadoEvento;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class EstadoEventoDAOImpl implements EstadoEventoDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public EstadoEvento create(EstadoEvento estadoEvento) throws ServicioException {
        try {
            EstadoEvento yaExiste = (EstadoEvento) em.createNamedQuery("EstadoEvento.findByNombre").setParameter("nombre", estadoEvento.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un estado de evento con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(estadoEvento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el estado de evento");
        }

        return estadoEvento;
    }

    @Override
    public EstadoEvento edit(EstadoEvento estadoEvento) throws ServicioException {
        try {
            em.merge(estadoEvento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el estado de evento");
        }

        return estadoEvento;
    }

    @Override
    public void remove(EstadoEvento estadoEvento) throws ServicioException {
        try {
            em.remove(estadoEvento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el estado de evento");
        }
    }

    @Override
    public EstadoEvento findById(Long id) {
        EstadoEvento estadoEvento = em.find(EstadoEvento.class, id);

        return estadoEvento;
    }

    @Override
    public List<EstadoEvento> findAll() {
        List<EstadoEvento> estadosEvento = em.createNamedQuery("EstadoEvento.findAll").getResultList();

        return estadosEvento;
    }

}