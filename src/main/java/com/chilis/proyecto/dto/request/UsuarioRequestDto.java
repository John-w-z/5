package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class UsuarioRequestDto {
    private Long idPersonal;
    private Long idRol;
    private String usuario;
    private String password;
    private String pin;
}