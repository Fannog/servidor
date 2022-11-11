package com.fannog.servidor.utils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.jasypt.util.password.StrongPasswordEncryptor;

@LocalBean
@Stateless
public class Encryptor {

    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    public Encryptor() {
    }

    public String encryptPassword(String password) {
        String result = passwordEncryptor.encryptPassword(password);

        return result;
    }

    public boolean checkPassword(String plainText, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainText, encryptedPassword);
    }
}
