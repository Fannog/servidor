package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.entities.Tutor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.TutorDAO;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Stateless
public class TutorDAOImpl implements TutorDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Tutor create(Tutor tutor) throws ServicioException {
        try {
            em.persist(tutor);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el tutor");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }

        return tutor;
    }

    @Override
    public Tutor edit(Tutor tutor) throws ServicioException {
        try {
            em.merge(tutor);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el tutor");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }

        return tutor;
    }

    @Override
    public void remove(Tutor tutor) throws ServicioException {
        try {
            em.remove(tutor);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar eliminar el tutor");
        } 
    }

    @Override
    public Tutor findById(Long id) {
        Tutor tutor = (Tutor) em.createNativeQuery("Tutor.findById").setParameter(1, id).getSingleResult();

        return tutor;
    }

    @Override
    public List<Tutor> findAll() {
        List<Tutor> tutores = em.createNamedQuery("Tutor.findAll").getResultList();

        return tutores;
    }

}
