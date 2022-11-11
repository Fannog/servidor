package com.fannog.servidor.DAO.impl;

import com.fannog.servidor.entities.Evento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.servidor.DAO.EventoDAO;
import com.fannog.servidor.exceptions.ServicioException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Stateless
public class EventoDAOImpl implements EventoDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Evento create(Evento evento) throws ServicioException {
        try {
            em.persist(evento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el evento");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }

        return evento;
    }

    @Override
    public Evento edit(Evento evento) throws ServicioException {
        try {
            em.merge(evento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el evento");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }

        return evento;
    }

    @Override
    public void remove(Evento evento) throws ServicioException {
        try {
            em.remove(evento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el evento");
        }
    }

    @Override
    public Evento findById(Long id) {
        Evento evento = em.find(Evento.class, id);

        return evento;
    }

    @Override
    public List<Evento> findAll() {
        List<Evento> eventos = em.createNamedQuery("Evento.findAll").getResultList();

        return eventos;
    }

}
