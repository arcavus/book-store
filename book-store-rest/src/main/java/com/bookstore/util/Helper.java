package com.bookstore.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helper {

    public static BCryptPasswordEncoder getEncryption() {
        return new BCryptPasswordEncoder();
    }

    public static String encryptionValue(String value) {
        BCryptPasswordEncoder encoder = getEncryption();
        return encoder.encode(value);
    }
}
