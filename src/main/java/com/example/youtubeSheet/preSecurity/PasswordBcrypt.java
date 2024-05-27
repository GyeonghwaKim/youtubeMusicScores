package com.example.youtubeSheet.preSecurity;


import org.mindrot.jbcrypt.BCrypt;

//@Component
public class PasswordBcrypt implements EncryptHelper {


    @Override
    public String encrypt(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public Boolean isMatch(String password, String hashPassword) {
        return BCrypt.checkpw(password,hashPassword);
    }
}
