package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class RolRequestDto {
    private String nombre;
    private String descripcion;
    private Boolean activo;
}