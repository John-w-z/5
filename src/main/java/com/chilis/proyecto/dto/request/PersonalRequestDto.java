package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class PersonalRequestDto {
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
}