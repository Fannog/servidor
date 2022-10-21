package com.fannog.proyectoservidor.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.AnalistaDAO;
import com.fannog.proyectoservidor.entities.Analista;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.PersistenceException;

@Stateless
public class AnalistaDAOImpl implements AnalistaDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Analista create(Analista analista) throws ServicioException {
        try {
            em.persist(analista);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el analista");
        }

        return analista;
    }

    @Override
    public Analista edit(Analista analista) throws ServicioException {
        try {
            em.merge(analista);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el analista");
        }

        return analista;
    }

    @Override
    public void remove(Analista analista) throws ServicioException {
        try {
            em.remove(analista);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el analista");
        }
    }

    @Override
    public Analista findById(Long id) {
        Analista analista = em.find(Analista.class, id);

        return analista;
    }

    @Override
    public List<Analista> findAll() {
        List<Analista> analistas = em.createNamedQuery("Analista.findAll").getResultList();

        return analistas;
    }

}
