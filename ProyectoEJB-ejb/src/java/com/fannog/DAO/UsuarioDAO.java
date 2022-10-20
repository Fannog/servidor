package com.fannog.DAO;

import com.fannog.entities.Usuario;
import com.fannog.exceptions.ServicioException;
import javax.ejb.Remote;

@Remote
public interface UsuarioDAO extends DAO<Usuario> {

    Usuario login(String userName, String password) throws ServicioException;
}
