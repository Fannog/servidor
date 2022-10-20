package com.fannog.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.UsuarioDAO;
import com.fannog.entities.Usuario;
import com.fannog.exceptions.ServicioException;
import com.fannog.utils.Encryptor;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

@Stateless
public class UsuarioDAOImpl implements UsuarioDAO {

    @Inject
    Encryptor encryptor;

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public Usuario create(Usuario usuario) throws ServicioException {
        try {
            Usuario yaExiste = (Usuario) em.createNamedQuery("Usuario.findByDocumento").setParameter("documento", usuario.getDocumento()).getSingleResult();

            if (yaExiste != null) {
                throw new ServicioException("El usuario ya existe");
            }
        } catch (NoResultException e) {
        }

        try {
            em.persist(usuario);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear el usuario");
        }

        return usuario;
    }

    @Override
    public Usuario edit(Usuario usuario) throws ServicioException {
        try {
            em.merge(usuario);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar el usuario");
        }

        return usuario;
    }

    @Override
    public void remove(Usuario usuario) throws ServicioException {
        try {
            usuario.setEliminado(true);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja el usuario");
        }
    }

    @Override
    public Usuario findById(Long id) {
        Usuario usuario = em.find(Usuario.class, id);

        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = em.createNamedQuery("Usuario.findAll").getResultList();

        return usuarios;
    }

    @Override
    public Usuario login(String userName, String password) throws ServicioException {
        Usuario usuario = null;

        try {
            usuario = (Usuario) em.createNamedQuery("Usuario.findByNombreUsuario").setParameter("nombreUsuario", userName).getSingleResult();

            boolean valid = encryptor.checkPassword(password, usuario.getPassword());

            if (!valid) {
                throw new ServicioException("Contraseña incorrecta");
            }

        } catch (NoResultException e) {
            throw new ServicioException("El usuario no existe");
        }

        return usuario;
    }

}
