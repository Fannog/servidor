package com.fannog.localidad;

import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LocalidadDAOImpl implements LocalidadDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Localidad add(Localidad entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Localidad update(Localidad entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Localidad entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
