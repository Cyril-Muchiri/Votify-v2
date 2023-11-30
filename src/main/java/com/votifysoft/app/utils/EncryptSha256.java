package com.votifysoft.app.utils;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Priority(1)
@Alternative
public class EncryptSha256 implements EncryptPwd {

    public String encrypt(String text) {
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        messageDigest.update(text.getBytes());

        return new String(messageDigest.digest());
    }
}
