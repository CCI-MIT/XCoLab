package org.xcolab.service.members.util;

import org.apache.http.impl.auth.UnsupportedDigestAlgorithmException;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Base64;

public class SHA1PasswordEncryptor {

    public static final String ALGORITHM_NAME = "SHA-1";

    public String doEncrypt(String algorithm, String plainTextPassword) throws Exception {

        if (!ALGORITHM_NAME.equals(algorithm)) {
            throw new UnsupportedDigestAlgorithmException("This encryptor does not support the algorithm" + algorithm);
        }

        try {
            MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");

            byte[] secretKeyBytes = sha1Digest.digest(plainTextPassword.getBytes());

            ByteBuffer byteBuffer = ByteBuffer.allocate(secretKeyBytes.length);
            byteBuffer.put(secretKeyBytes);
            return Base64.getEncoder().encodeToString(byteBuffer.array());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }
}