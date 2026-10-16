package com.chilis.proyecto.dto.response;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String usuario;
    private String token;
    private String tipo = "Bearer";
    private String rol;
    private String nombresCompletos;
}