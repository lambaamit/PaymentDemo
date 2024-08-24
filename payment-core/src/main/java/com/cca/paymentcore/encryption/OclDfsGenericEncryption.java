package com.cca.paymentcore.encryption;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Class For WebEncryption
 */
@Component
@Slf4j
public class OclDfsGenericEncryption {

    private OclDfsGenericEncryption() {
    }

    /**
     * Method To Encrypt
     *
     * @param value
     * @return String
     */
    public static String encrypt(String value, String key) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] encryptedBytes = encryptCipher.doFinal(value.getBytes());
            return new String(Base64.getEncoder().encode(encryptedBytes));
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
        return StringUtils.EMPTY;
    }

    public static String decrypt(String encrypted, String key) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decryptedBytes);
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
        return StringUtils.EMPTY;
    }
}
