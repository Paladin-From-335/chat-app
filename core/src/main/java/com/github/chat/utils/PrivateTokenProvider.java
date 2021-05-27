package com.github.chat.utils;

import com.github.chat.exceptions.BadRequest;
import com.github.chat.payload.PrivateToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

public class PrivateTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(PrivateTokenProvider.class);

    private static final String SECRET_KEY = "MakeItBunDem";

    private static final String SALT = "ServletChat";

    private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    public static String encode(PrivateToken t) {
        if (t == null) {
            throw new BadRequest("Empty token!");
        }
        String str = JsonHelper.toJson(t).get();
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("Error while encrypting: " + e);
        }
        log.info("Cipher token!");
        return null;
    }

    public static PrivateToken decode(String str) {
        PrivateToken newT = null;

        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            newT = JsonHelper.fromJson(new String(cipher.doFinal(Base64.getDecoder().decode(str))), PrivateToken.class).orElse(null);
        } catch (Exception e) {
            log.error("Error while decrypting: " + e);
        }
        return newT;
    }

    public static boolean checkToken(PrivateToken token) {
        if (token == null) {
            log.info("Token is null!");
            return false;
        }
        if (token.getExpireIn().compareTo(new Date()) < 0) {
            log.info("Token expired!");
            return false;
        }
        return true;
    }
}
