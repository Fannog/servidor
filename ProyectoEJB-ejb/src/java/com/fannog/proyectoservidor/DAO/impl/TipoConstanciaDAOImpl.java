package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.entities.TipoConstancia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.TipoConstanciaDAO;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class TipoConstanciaDAOImpl implements TipoConstanciaDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public TipoConstancia create(TipoConstancia tipoConstancia) throws ServicioException {
        try {
            TipoConstancia yaExiste = (TipoConstancia) em.createNamedQuery("TipoConstancia.findByNombre").setParameter("nombre", tipoConstancia.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un tipo de constancia con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(tipoConstancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el tipo de constancia");
        }

        return tipoConstancia;
    }

    @Override
    public TipoConstancia edit(TipoConstancia tipoConstancia) throws ServicioException {
        try {
            em.merge(tipoConstancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el tipo de constancia");
        }

        return tipoConstancia;
    }

    @Override
    public void remove(TipoConstancia tipoConstancia) throws ServicioException {
        try {
            em.remove(tipoConstancia);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el tipo de constancia");
        }
    }

    @Override
    public TipoConstancia findById(Long id) {
        TipoConstancia tipoConstancia = em.find(TipoConstancia.class, id);

        return tipoConstancia;

    }

    @Override
    public List<TipoConstancia> findAll() {
        List<TipoConstancia> tipoConstancia = em.createNamedQuery("TipoConstancia.findAll").getResultList();

        return tipoConstancia;

    }

}
