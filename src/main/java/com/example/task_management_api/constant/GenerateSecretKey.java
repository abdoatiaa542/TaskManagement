//package com.example.task_management_api.constant;
//
//import io.jsonwebtoken.security.Keys;
//
//import javax.crypto.SecretKey;
//
//
//public class GenerateSecretKey {
//    public static void main(String[] args) {
//        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//        System.out.println(java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
//    }
//}