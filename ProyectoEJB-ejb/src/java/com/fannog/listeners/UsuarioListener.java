package com.fannog.usuario;


import com.fannog.encryptor.Encryptor;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UsuarioListener {

    @Inject
    Encryptor encryptor;
    
    @PrePersist
    @PreUpdate
    public void prePersistAndUpdateUser(Usuario usuario) {
        String email = usuario.getEmailInstitucional();
        String password = usuario.getPassword();

        String encryptedPassword = encryptor.encryptPassword(password);
        usuario.setPassword(encryptedPassword);

        String userName = email.substring(0, email.lastIndexOf("@"));
        usuario.setNombreUsuario(userName);
    }

}
