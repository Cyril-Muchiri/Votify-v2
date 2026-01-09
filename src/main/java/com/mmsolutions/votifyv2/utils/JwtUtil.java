package com.mmsolutions.votifyv2.utils;

import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {

    private static final String SECRET = "my-secret-key";

    public static String generateToken(String username) throws Exception {
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        long now = System.currentTimeMillis();
        long exp = now + 1000 * 60 * 60; // 1 hour expiry
        String payload = String.format("{\"sub\":\"%s\",\"iat\":%d,\"exp\":%d}", username, now / 1000, exp / 1000);

        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
        String signature = hmacSha256(encodedHeader + "." + encodedPayload, SECRET);

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    public static boolean validateToken(String token) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) return false;

        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];

        String expectedSig = hmacSha256(header + "." + payload, SECRET);
        if (!expectedSig.equals(signature)) return false;

        String payloadJson = new String(Base64.getUrlDecoder().decode(payload));
        long exp = Long.parseLong(payloadJson.replaceAll(".*\"exp\":(\\d+).*", "$1"));
        long now = System.currentTimeMillis() / 1000;

        return now < exp;
    }

    private static String hmacSha256(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(data.getBytes()));
    }

    public static String extractUsername(String token) {
        String payload = token.split("\\.")[1];
        String payloadJson = new String(Base64.getUrlDecoder().decode(payload));
        return payloadJson.replaceAll(".*\"sub\":\"([^\"]+)\".*", "$1");
    }
}
