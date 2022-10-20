package com.fannog.DAO.impl;

import com.fannog.entities.Justificacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.JustificacionDAO;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class JustificacionDAOImpl implements JustificacionDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Justificacion create(Justificacion justificacion) throws ServicioException {
        try {
            em.persist(justificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear la justificacion");
        }

        return justificacion;
    }

    @Override
    public Justificacion edit(Justificacion justificacion) throws ServicioException {
        try {
            em.merge(justificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la justificacion");
        }

        return justificacion;
    }

    @Override
    public void remove(Justificacion justificacion) throws ServicioException {
        try {
            em.remove(justificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la justificacion");
        }
    }

    @Override
    public Justificacion findById(Long id) {
        Justificacion justificacion = em.find(Justificacion.class, id);

        return justificacion;
    }

    @Override
    public List<Justificacion> findAll() {
        List<Justificacion> justificaciones = em.createNamedQuery("Justificacion.findAll").getResultList();

        return justificaciones;
    }

}
