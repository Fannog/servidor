package com.fannog.proyectoservidor.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.DepartamentoDAO;
import com.fannog.proyectoservidor.entities.Departamento;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class DepartamentoDAOImpl implements DepartamentoDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Departamento create(Departamento departamento) throws ServicioException {
        try {
            Departamento yaExiste = (Departamento) em.createNamedQuery("Departamento.findByNombre").setParameter("nombre", departamento.getNombre()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("Ya existe un departamento con este nombre");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(departamento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el departamento");
        }

        return departamento;
    }

    @Override
    public Departamento edit(Departamento departamento) throws ServicioException {
        try {
            em.merge(departamento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el departamento");
        }

        return departamento;
    }

    @Override
    public void remove(Departamento departamento) throws ServicioException {
        try {
            em.remove(departamento);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el departamento");
        }

    }

    @Override
    public Departamento findById(Long id) {
        Departamento departamento = em.find(Departamento.class, id);

        return departamento;
    }

    @Override
    public List<Departamento> findAll() {
        List<Departamento> departamentos = em.createNamedQuery("Departamento.findAll").getResultList();

        return departamentos;
    }

}