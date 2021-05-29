package com.github.chat.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretCodeProvider {

    public static String getSecretCode() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String secretCode = encoder.encodeToString(bytes);
        return secretCode;
    }
}
