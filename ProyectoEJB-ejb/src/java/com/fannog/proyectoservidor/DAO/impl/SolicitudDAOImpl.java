package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.entities.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.SolicitudDAO;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

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
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
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
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
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
