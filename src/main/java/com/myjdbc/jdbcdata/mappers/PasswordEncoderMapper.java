package com.myjdbc.jdbcdata.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String rawPassword) {
        return rawPassword != null ? passwordEncoder.encode(rawPassword) : null;
    }
}
