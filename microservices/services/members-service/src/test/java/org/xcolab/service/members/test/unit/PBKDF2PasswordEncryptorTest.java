package org.xcolab.service.members.test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.xcolab.service.members.util.PBKDF2PasswordEncryptor;

public class PBKDF2PasswordEncryptorTest {

    private static final String ALGORITHM = "PBKDF2WITHHMACSHA1/160/128000";

    @Test
    public void doEncrypt() throws Exception {
        PBKDF2PasswordEncryptor encryptor = new PBKDF2PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM, "colab123", "");
        Assert.assertEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab123", hashed));
    }

    @Test
    public void doEncrypt__different() throws Exception {
        PBKDF2PasswordEncryptor encryptor = new PBKDF2PasswordEncryptor();
        final String hashed = encryptor.doEncrypt(ALGORITHM, "colab123", "");
        Assert.assertNotEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab1234", hashed));
    }

    @Test
    public void doEncrypt__matchesLiferay() throws Exception {
        PBKDF2PasswordEncryptor encryptor = new PBKDF2PasswordEncryptor();
        final String hashed = "AAAAoAAB9ADzAoRz43N2Y51OLzokXCcy0xn743H07LCx2l5R";
        Assert.assertEquals(hashed, encryptor.doEncrypt(ALGORITHM, "colab123", hashed));
    }
}