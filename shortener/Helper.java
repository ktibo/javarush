package com.javarush.shortener;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Helper {

    public static String generateRandomString() {

        SecureRandom instanceStrong = null;
        try {
            instanceStrong = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int length = instanceStrong.nextInt(1, 100);
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int letter = instanceStrong.nextInt(97, 123);
            buffer.append((char)letter);
        }
        return buffer.toString();

    }

    public static void printMessage(String message) {
        System.out.println(message);
    }



}
