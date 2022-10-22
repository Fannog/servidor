package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.entities.Localidad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.LocalidadDAO;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Stateless
public class LocalidadDAOImpl implements LocalidadDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Localidad create(Localidad localidad) throws ServicioException {
        try {
            em.persist(localidad);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear la localidad");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }
        
        return localidad;
    }

    @Override
    public Localidad edit(Localidad localidad) throws ServicioException {
        try {
            em.merge(localidad);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la localidad");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }
        
        return localidad;
    }

    @Override
    public void remove(Localidad localidad) throws ServicioException {
         try {
            em.remove(localidad);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la localidad");
        }
    }

    @Override
    public Localidad findById(Long id) {
        Localidad localidad = em.find(Localidad.class, id);
        
        return localidad;
    }

    @Override
    public List<Localidad> findAll() {
        List<Localidad> localidades = em.createNamedQuery("Localidad.findAll").getResultList();
        
        return localidades;
    }

}
