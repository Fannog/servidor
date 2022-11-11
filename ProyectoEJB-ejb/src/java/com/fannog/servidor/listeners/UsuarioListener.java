package com.fannog.servidor.listeners;

import com.fannog.servidor.entities.Usuario;
import com.fannog.servidor.utils.Encryptor;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UsuarioListener {

    @Inject
    Encryptor encryptor;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdateUser(Usuario usuario) {
        String email = usuario.getEmail();
        String password = usuario.getPassword();

        if (password.length() >= 4 && password.length() <= 20) {
            String encryptedPassword = encryptor.encryptPassword(password);
            usuario.setPassword(encryptedPassword);
        }

        String userName = email.substring(0, email.lastIndexOf("@"));
        usuario.setNombreUsuario(userName);
    }

}
