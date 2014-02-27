package org.xcolab.portlets.loginregister.singlesignon;

/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import com.liferay.portal.PwdEncryptorException;
import com.liferay.portal.kernel.io.BigEndianCodec;
import com.liferay.portal.kernel.security.SecureRandomUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.security.pwd.BasePasswordEncryptor;
import com.liferay.portal.security.pwd.PasswordEncryptor;
import com.liferay.portal.security.pwd.PasswordEncryptorUtil;
import com.liferay.portal.util.*;
import sun.net.www.content.text.plain;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael C. Han
 * @author Tomas Polesovsky
 */
public class PBKDF2PasswordEncryptor
        extends BasePasswordEncryptor {

    public static final String PASSWORDS_ENCRYPTION_ALGORITHM =
            StringUtil.toUpperCase(
                    GetterUtil.getString(
                            com.liferay.portal.util.PropsUtil.get(PropsKeys.PASSWORDS_ENCRYPTION_ALGORITHM)));

    /**
     * Designated API method to encrypt passwords
     *
     * @param plainTextPassword
     * @param encryptedPassword
     * @return
     */
    public String encryptPassword(String plainTextPassword, String encryptedPassword) throws PwdEncryptorException {
        return doEncrypt(PASSWORDS_ENCRYPTION_ALGORITHM, plainTextPassword, encryptedPassword);
    }

    @Override
    public String[] getSupportedAlgorithmTypes() {
        return new String[] {PasswordEncryptorUtil.TYPE_PBKDF2};
    }

    @Override
    protected String doEncrypt(
            String algorithm, String plainTextPassword,
            String encryptedPassword)
            throws PwdEncryptorException {

        try {
            PBKDF2EncryptionConfiguration pbkdf2EncryptionConfiguration =
                    new PBKDF2EncryptionConfiguration();

            pbkdf2EncryptionConfiguration.configure(
                    algorithm, encryptedPassword);

            byte[] saltBytes = pbkdf2EncryptionConfiguration.getSaltBytes();

            PBEKeySpec pbeKeySpec = new PBEKeySpec(
                    plainTextPassword.toCharArray(), saltBytes,
                    pbkdf2EncryptionConfiguration.getRounds(),
                    pbkdf2EncryptionConfiguration.getKeySize());

            String algorithmName = algorithm;

            int index = algorithm.indexOf(CharPool.SLASH);

            if (index > -1) {
                algorithmName = algorithm.substring(0, index);
            }

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(
                    algorithmName);

            SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);

            byte[] secretKeyBytes = secretKey.getEncoded();

            ByteBuffer byteBuffer = ByteBuffer.allocate(
                    2 * 4 + saltBytes.length + secretKeyBytes.length);

            byteBuffer.putInt(pbkdf2EncryptionConfiguration.getKeySize());
            byteBuffer.putInt(pbkdf2EncryptionConfiguration.getRounds());
            byteBuffer.put(saltBytes);
            byteBuffer.put(secretKeyBytes);

            return Base64.encode(byteBuffer.array());
        }
        catch (Exception e) {
            throw new PwdEncryptorException(e.getMessage(), e);
        }
    }

    private static final int _KEY_SIZE = 160;

    private static final int _ROUNDS = 128000;

    private static final int _SALT_BYTES_LENGTH = 8;

    private static Pattern _pattern = Pattern.compile(
            "^.*/?([0-9]+)?/([0-9]+)$");

    private class PBKDF2EncryptionConfiguration {

        public void configure(String algorithm, String encryptedPassword)
                throws PwdEncryptorException {

            if (Validator.isNull(encryptedPassword)) {
                Matcher matcher = _pattern.matcher(algorithm);

                if (matcher.matches()) {
                    _keySize = GetterUtil.getInteger(
                            matcher.group(1), _KEY_SIZE);

                    _rounds = GetterUtil.getInteger(matcher.group(2), _ROUNDS);
                }

                BigEndianCodec.putLong(
                        _saltBytes, 0, SecureRandomUtil.nextLong());
            }
            else {
                byte[] bytes = new byte[16];

                try {
                    byte[] encryptedPasswordBytes = Base64.decode(
                            encryptedPassword);

                    System.arraycopy(
                            encryptedPasswordBytes, 0, bytes, 0, bytes.length);
                }
                catch (Exception e) {
                    throw new PwdEncryptorException(
                            "Unable to extract salt from encrypted password " +
                                    e.getMessage(),
                            e);
                }

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

        private int _keySize = _KEY_SIZE;
        private int _rounds = _ROUNDS;
        private byte[] _saltBytes = new byte[_SALT_BYTES_LENGTH];

    }

}