package com.example.attackontitan;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;

/** 2.5 Extra Feature */
public class AESCipher {

    // Class private variables
    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    public void initialize() throws Exception {
        // Generate one key using key instance of AES and assign it to keyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // Key sizes values can be 128, 192 or 256 bytes
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public String encrypt(String data) throws Exception {
        // Guess the byte array from the data String
        byte[] dataInBytes = data.getBytes();
        // Create an encryption cipher and get its instance
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        // Initialize encryption cipher
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        // Return byte array of encrypted data
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        // Convert data into byte array
        byte[] dataInBytes = decode(encryptedData);
        // Create decryptionCipher and get its instance of AES algorithm
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        // Initialize decryption cipher
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        // Get bytes array from decrypted bytes
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
        // Return new String
        return new String(decryptedBytes);
    }

    // To convert data into String
    private String encode(byte[] data) {
        // Take in bytes array and return to BASE64
        return Base64.getEncoder().encodeToString(data);
    }
    // To convert data into String
    private byte[] decode(String data) {
        // Take in bytes array and return to BASE64
        return Base64.getDecoder().decode(data);
    }

}