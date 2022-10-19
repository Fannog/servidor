package com.fannog.usuario;

import com.fannog.exceptions.ServicioException;
import com.fannog.shared.DAO;
import javax.ejb.Remote;

@Remote
public interface UsuarioDAO extends DAO<Usuario> {
    Usuario login(String userName, String password) throws ServicioException;
}
