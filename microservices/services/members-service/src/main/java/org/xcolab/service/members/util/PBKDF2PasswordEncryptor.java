package org.xcolab.service.members.util;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.commons.exceptions.InternalException;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class PBKDF2PasswordEncryptor {

    public static final String DEFAULT_ALGORITHM = "PBKDF2WITHHMACSHA1/160/128000";

    private static final int _KEY_SIZE = 160;
    private static final int _ROUNDS = 128000;
    private static final int _SALT_BYTES_LENGTH = 8;
    private static final Pattern ALGORITHM_PATTERN = Pattern.compile("^.*/?([0-9]+)?/([0-9]+)$");
    private static final Pattern ENCODED_HASH_PATTERN = Pattern.compile("^?([0-9]+)_([0-9]+)_([0-9A-Za-z+/=]+)_([0-9A-Za-z+/=]+)$");

    public String doEncrypt(String algorithm, String plainTextPassword, String encryptedPassword) {

        PBKDF2EncryptionConfiguration pbkdf2EncryptionConfiguration = new PBKDF2EncryptionConfiguration();

        pbkdf2EncryptionConfiguration.configure(algorithm, encryptedPassword);

        byte[] saltBytes = pbkdf2EncryptionConfiguration.getSaltBytes();

        PBEKeySpec pbeKeySpec = new PBEKeySpec(
                plainTextPassword.toCharArray(), saltBytes,
                pbkdf2EncryptionConfiguration.getRounds(),
                pbkdf2EncryptionConfiguration.getKeySize());

        String algorithmName = algorithm;

        int index = algorithm.indexOf("/");

        if (index > -1) {
            algorithmName = algorithm.substring(0, index);
        }

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithmName);

            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] secretKeyBytes = secretKey.getEncoded();

            return String.valueOf(pbkdf2EncryptionConfiguration.getKeySize()) +
                    "_" +
                    pbkdf2EncryptionConfiguration.getRounds() +
                    "_" +
                    DatatypeConverter.printBase64Binary(saltBytes) +
                    "_" +
                    DatatypeConverter.printBase64Binary(secretKeyBytes);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new InternalException("Internal error while hashing password", e);
        }
    }

    private static class PBKDF2EncryptionConfiguration {

        private int _keySize = _KEY_SIZE;
        private int _rounds = _ROUNDS;
        private final byte[] _saltBytes = new byte[_SALT_BYTES_LENGTH];

        public void configure(String algorithm, String encryptedPassword) {
            if (StringUtils.isEmpty(encryptedPassword)) {
                Matcher matcher = ALGORITHM_PATTERN.matcher(algorithm);

                if (matcher.matches()) {
                    try {
                        _keySize = Integer.parseInt(matcher.group(1));
                    } catch (NumberFormatException e) {
                        _keySize = _KEY_SIZE;
                    }
                    try {
                        _rounds = Integer.parseInt(matcher.group(2));
                    } catch (NumberFormatException e) {
                        _rounds = _ROUNDS;
                    }
                }

                BigEndianCodec.putLong(_saltBytes, 0, SecureRandomUtil.nextLong());
            } else {
                Matcher matcher = ENCODED_HASH_PATTERN.matcher(encryptedPassword);
                if (matcher.matches()) {
                    try {
                        _keySize = Integer.parseInt(matcher.group(1));
                    } catch (NumberFormatException e) {
                        _keySize = _KEY_SIZE;
                    }
                    try {
                        _rounds = Integer.parseInt(matcher.group(2));
                    } catch (NumberFormatException e) {
                        _rounds = _ROUNDS;
                    }
                    BigEndianCodec.putLong(_saltBytes, 0, new BigInteger(
                            DatatypeConverter.parseBase64Binary(matcher.group(3))).longValue());
                }
            }
        }

        public int getKeySize() {
            return _keySize;
        }

        public int getRounds() {
            return _rounds;
        }

        public byte[] getSaltBytes() {
            return _saltBytes;
        }
    }
}
