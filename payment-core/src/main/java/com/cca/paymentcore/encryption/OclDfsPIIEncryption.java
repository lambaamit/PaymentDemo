package com.cca.paymentcore.encryption;

import com.cca.paymentcore.constants.ApplicationConstants;
import com.cca.paymentcore.service.loggerservice.LogService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Class to Encrypt and Decrypt Values
 */
@Component
@Slf4j
@SuppressWarnings("java:S6813")
public class OclDfsPIIEncryption {

    private static Cipher cipher;
    private static SecretKeySpec skeySpec;
    private static IvParameterSpec iv;
    private final String className = this.getClass().getSimpleName();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Value("${pii.key}")
    private String key;
    @Value("${pii.initVector}")
    private String initVector;

    private OclDfsPIIEncryption() {
    }

    /**
     * Method To Encrypt
     *
     * @param value
     * @return
     */
    public static String encrypt(String value) {
        try {
            /* skeySpec - secretKey */
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
//			log.error(ExceptionUtils.getStackTrace(ex));
        }
        return value;
    }

    /**
     * Method To Decrypt
     *
     * @param encrypted
     * @return
     */
    public static String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
//			log.error(ExceptionUtils.getStackTrace(ex));
        }
        return encrypted;
    }

    public static String mD5(String md5) {
        try {
            java.security.MessageDigest md = DigestUtils.getMd5Digest();
            byte[] array = md.digest(md5.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (Exception ex) {
//			log.error(ExceptionUtils.getStackTrace(ex));
        }
        return null;
    }

    /**
     * Set SKey
     *
     * @param mainKey
     */
    private static void setSKeySpec(String mainKey) {
        //Secret key object
        skeySpec = new SecretKeySpec(mainKey.getBytes(StandardCharsets.UTF_8), "AES");
    }

    /**
     * Set Initial Vector
     *
     * @param initVector
     */
    @SuppressWarnings("java:S3329")
    private static void setInitialVector(String initVector) {
        iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Set Cipher
     *
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    @SuppressWarnings("java:S5542")
    private static void setCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(ApplicationConstants.AES_CBC_PKCS5PADDING);
    }

    @PostConstruct
    public void setup() {
        String mainKey = null;
        try {
            mainKey = mD5(key);
            if (mainKey == null)
                throw new NullPointerException("Main Key is null");
            setSKeySpec(mainKey);
            setInitialVector(initVector);
            setCipher();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            logService.printErrorLogs(request, className, e);
        }
    }

    @Override
    public String toString() {
        return "One97PayWalletEncription []";
    }
}