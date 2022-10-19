package com.fannog.usuario;

import com.fannog.encryptor.Encryptor;
import com.fannog.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class UsuarioDAOImpl implements UsuarioDAO {

    @Inject
    Encryptor encryptor;
    
    @PersistenceContext()
    private EntityManager em;

    @Override
    public Usuario add(Usuario usuario) throws ServicioException {
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
            System.out.println(usuario.getPassword());
            throw new ServicioException("Ha ocurrido un error");
        }

        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Usuario usuario) throws ServicioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        
        System.out.println("LOGIN EXITOSOoooooooooooooooooo!!!");
        
        return usuario;
    }

}
