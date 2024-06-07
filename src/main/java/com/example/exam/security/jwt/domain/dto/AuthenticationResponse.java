package com.example.exam.security.jwt.domain.dto;

import lombok.Data;

@Data

public class AuthenticationResponse {
    private final String jwt;

}