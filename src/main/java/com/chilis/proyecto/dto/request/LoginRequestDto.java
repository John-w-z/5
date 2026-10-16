package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String usuario;
    private String password;
}