package org.xcolab.portlets.loginregister.singlesignon;

/**
 * Created by Mente on 27.02.14.
 */
public interface PasswordEncryptor {

    public String encrypt(String plainPassword, String encrPassword);
}
