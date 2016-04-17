package org.xcolab.service.members.util;

import org.junit.Assert;
import org.junit.Test;

public class SHA1PasswordEncryptorTest {

    private static final String ALGORITHM = "SHA-1";

    @Test
    public void doEncrypt() throws Exception {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM, "colab123");
        Assert.assertEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab123"));
    }

    @Test
    public void doEncrypt__different() throws Exception {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM, "colab123");
        Assert.assertNotEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab1234"));
    }

    @Test
    public void doEncrypt__matchesLiferay() throws Exception {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = "NS2PVQOqtxe9YlFzf3xNa8/6XDo=";
        Assert.assertEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab123"));
    }
}