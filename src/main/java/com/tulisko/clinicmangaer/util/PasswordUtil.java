package com.tulisko.clinicmangaer.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtil {

    private static final int ITERATIONS  = 120_000;
    private static final int KEY_LENGTH = 256;
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtil(){}

    public static String generateSlat(){
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);

    }

    public static String hashPassword(String password,String salt){
        try {
            PBEKeySpec spec= new PBEKeySpec(password.toCharArray(),Base64.getDecoder().decode(salt),ITERATIONS,KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        }catch (Exception e){
            throw new RuntimeException("error quand hash le mot de pass", e);
        }
    }

    public static boolean mathces(String password, String salt,String passwordHash){
        String hash = hashPassword(password,salt);
        return passwordHash.equals(hash);
    }

}
