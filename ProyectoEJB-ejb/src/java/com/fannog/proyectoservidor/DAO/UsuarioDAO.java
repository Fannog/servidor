package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Usuario;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import javax.ejb.Remote;

@Remote
public interface UsuarioDAO extends DAO<Usuario> {

    Usuario login(String userName, String password) throws ServicioException;
    
    Usuario findByNombreUsuario(String nombreUsuario) throws ServicioException;
}
