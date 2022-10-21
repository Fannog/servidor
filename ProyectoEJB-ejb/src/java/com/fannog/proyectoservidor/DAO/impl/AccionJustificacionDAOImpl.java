package com.fannog.proyectoservidor.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.AccionJustificacionDAO;
import com.fannog.proyectoservidor.entities.AccionJustificacion;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class AccionJustificacionDAOImpl implements AccionJustificacionDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public AccionJustificacion create(AccionJustificacion accionJustificacion) throws ServicioException {
        try {
            em.persist(accionJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar registrar la accion");
        }

        return accionJustificacion;
    }

    @Override
    public AccionJustificacion edit(AccionJustificacion accionJustificacion) throws ServicioException {
        try {
            em.merge(accionJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la acción");
        }

        return accionJustificacion;
    }

    @Override
    public void remove(AccionJustificacion accionJustificacion) throws ServicioException {
        try {
            em.remove(accionJustificacion);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la acción");
        }
    }

    @Override
    public AccionJustificacion findById(Long id) {
        AccionJustificacion accionJustificacion = em.find(AccionJustificacion.class, id);

        return accionJustificacion;
    }

    @Override
    public List<AccionJustificacion> findAll() {
        List<AccionJustificacion> accionJustificacion = em.createNamedQuery("AccionJustificacion.findAll").getResultList();

        return accionJustificacion;
    }

}
