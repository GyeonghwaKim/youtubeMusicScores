package com.example.youtubeSheet.preSecurity;

public interface EncryptHelper {

    String encrypt(String password);

    Boolean isMatch(String password,String hashPassword);
}
