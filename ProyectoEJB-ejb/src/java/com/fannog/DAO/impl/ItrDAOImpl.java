package com.fannog.DAO.impl;

import com.fannog.entities.Itr;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.ItrDAO;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class ItrDAOImpl implements ItrDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Itr create(Itr itr) throws ServicioException {
        try {
            Itr yaExiste = (Itr) em.createNamedQuery("Itr.findByNombre").setParameter("nombre", itr.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un ITR con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(itr);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el ITR");
        }

        return itr;
    }

    @Override
    public Itr edit(Itr itr) throws ServicioException {
        try {
            em.merge(itr);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el ITR");
        }

        return itr;
    }

    @Override
    public void remove(Itr itr) throws ServicioException {
        try {
            em.remove(itr);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el ITR");
        }
    }

    @Override
    public Itr findById(Long id) {
        Itr itr = em.find(Itr.class, id);

        return itr;
    }

    @Override
    public List<Itr> findAll() {
        List<Itr> itrs = em.createNamedQuery("Itr.findAll").getResultList();

        return itrs;
    }

}
