package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.entities.Estudiante;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.EstudianteDAO;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class EstudianteDAOImpl implements EstudianteDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Estudiante create(Estudiante estudiante) throws ServicioException {
        try {
            em.persist(estudiante);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el estudiante");
        }

        return estudiante;
    }

    @Override
    public Estudiante edit(Estudiante estudiante) throws ServicioException {
        try {
            em.merge(estudiante);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el estudiante");
        }

        return estudiante;
    }

    @Override
    public void remove(Estudiante estudiante) throws ServicioException {
        try {
            em.remove(estudiante);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el estudiante");
        }
    }

    @Override
    public Estudiante findById(Long id) {
        Estudiante estudiante = em.find(Estudiante.class, id);

        return estudiante;
    }

    @Override
    public List<Estudiante> findAll() {
        List<Estudiante> estudiantes = em.createNamedQuery("Estudiante.findAll").getResultList();

        return estudiantes;
    }

}
