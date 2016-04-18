package org.xcolab.service.members.util;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PBKDF2PasswordEncryptor {
    private static final int _KEY_SIZE = 160;
    private static final int _ROUNDS = 128000;
    private static final int _SALT_BYTES_LENGTH = 8;
    private static final Pattern _pattern = Pattern.compile("^.*/?([0-9]+)?/([0-9]+)$");

    public String doEncrypt(String algorithm, String plainTextPassword, String encryptedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

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

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithmName);

            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] secretKeyBytes = secretKey.getEncoded();

            ByteBuffer byteBuffer = ByteBuffer.allocate(2 * 4 + saltBytes.length + secretKeyBytes.length);

            byteBuffer.putInt(pbkdf2EncryptionConfiguration.getKeySize());
            byteBuffer.putInt(pbkdf2EncryptionConfiguration.getRounds());
            byteBuffer.put(saltBytes);
            byteBuffer.put(secretKeyBytes);
            return Base64.getEncoder().encodeToString(byteBuffer.array());
    }

    private class PBKDF2EncryptionConfiguration {

        private int _keySize = _KEY_SIZE;
        private int _rounds = _ROUNDS;
        private final byte[] _saltBytes = new byte[_SALT_BYTES_LENGTH];

        public void configure(String algorithm, String encryptedPassword) {
            if (StringUtils.isEmpty(encryptedPassword)) {
                Matcher matcher = _pattern.matcher(algorithm);

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
                byte[] bytes = new byte[16];

                byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
                System.arraycopy(encryptedPasswordBytes, 0, bytes, 0, bytes.length);

                ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

                _keySize = byteBuffer.getInt();
                _rounds = byteBuffer.getInt();

                byteBuffer.get(_saltBytes);
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