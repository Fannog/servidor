package com.fannog.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.ConstanciaDAO;
import com.fannog.entities.Constancia;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class ConstanciaDAOImpl implements ConstanciaDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Constancia create(Constancia constancia) throws ServicioException {
        try {
            em.persist(constancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear la constancia");
        }

        return constancia;
    }

    @Override
    public Constancia edit(Constancia constancia) throws ServicioException {
        try {
            em.merge(constancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la constancia");
        }

        return constancia;
    }

    @Override
    public void remove(Constancia constancia) throws ServicioException {
        try {
            em.remove(constancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la constancia");
        }
    }

    @Override
    public Constancia findById(Long id) {
        Constancia constancia = em.find(Constancia.class, id);

        return constancia;
    }

    @Override
    public List<Constancia> findAll() {
        List<Constancia> constancias = em.createNamedQuery("Constancia.findAll").getResultList();

        return constancias;
    }

}
