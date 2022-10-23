package com.fannog.proyectoservidor.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.UsuarioDAO;
import com.fannog.proyectoservidor.entities.Usuario;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import com.fannog.proyectoservidor.utils.Encryptor;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import static jdk.internal.util.StaticProperty.userName;

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
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
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
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
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

    @Override
    public Usuario findByNombreUsuario(String nombreUsuario) throws ServicioException {
        Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByNombreUsuario").setParameter("nombreUsuario", nombreUsuario).getSingleResult();
        return usuario;
    }

}
