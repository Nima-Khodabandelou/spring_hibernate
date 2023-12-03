package io.nkh.hibernate.service;

public interface EncryptionService {
    String encrypt(String freeText);

    String decrypt(String encryptedText);
}
