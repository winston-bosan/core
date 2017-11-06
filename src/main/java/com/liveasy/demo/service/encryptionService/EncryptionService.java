package com.liveasy.demo.service.encryptionService;

public interface EncryptionService {

    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);

}
