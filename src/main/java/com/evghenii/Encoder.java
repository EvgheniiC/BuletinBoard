package com.evghenii;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
    public static void main(String[] args) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("123456"));
    }
}
