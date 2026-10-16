package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class ClienteRequestDto {
    private String nombres;
    private String apellidos;
    private String documento;
    private String telefono;
    private String correo;
}