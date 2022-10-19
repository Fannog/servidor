package com.fannog.estadosUsuario;

import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadosUsuarioDAOImpl implements EstadosUsuarioDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public EstadosUsuario add(EstadosUsuario entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public EstadosUsuario update(EstadosUsuario entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void delete(EstadosUsuario entity) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public EstadosUsuario findById(Long id) {
        EstadosUsuario estadoUsuario = em.find(EstadosUsuario.class, id);

        return estadoUsuario;
    }

    @Override
    public List<EstadosUsuario> findAll() {
        List<EstadosUsuario> estadosUsuario = em.createNamedQuery("EstadosUsuario.findAll").getResultList();

        return estadosUsuario;
    }

}
