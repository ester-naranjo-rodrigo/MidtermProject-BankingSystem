package com.ironhack.MidtermProjectBankingSystem.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.time.*;

public class PasswordUtil {

    public static String encryptPassword(String plainPassword) {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        return passwordEncoder.encode(plainPassword);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
    }
}
