package org.xcolab.service.members.test.unit;

import org.apache.http.impl.auth.UnsupportedDigestAlgorithmException;
import org.junit.Assert;
import org.junit.Test;

import org.xcolab.service.members.util.SHA1PasswordEncryptor;

import java.security.NoSuchAlgorithmException;

public class SHA1PasswordEncryptorTest {

    private static final String ALGORITHM_SHA1 = SHA1PasswordEncryptor.ALGORITHM_NAME;

    @Test
    public void doEncrypt() throws UnsupportedDigestAlgorithmException, NoSuchAlgorithmException {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM_SHA1, "colab123");
        Assert.assertEquals(hashed,
                encryptor.doEncrypt(ALGORITHM_SHA1, "colab123"));
    }

    @Test
    public void doEncrypt__different() throws UnsupportedDigestAlgorithmException, NoSuchAlgorithmException {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM_SHA1, "colab123");
        Assert.assertNotEquals(hashed,
                encryptor.doEncrypt(ALGORITHM_SHA1, "colab1234"));
    }

    @Test
    public void doEncrypt__matchesLiferay() throws UnsupportedDigestAlgorithmException, NoSuchAlgorithmException {
        SHA1PasswordEncryptor encryptor = new SHA1PasswordEncryptor();
        final String hashed = "NS2PVQOqtxe9YlFzf3xNa8/6XDo=";
        Assert.assertEquals(hashed,
                encryptor.doEncrypt(ALGORITHM_SHA1, "colab123"));
    }
}