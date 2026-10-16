package com.chilis.proyecto.dto.response;

import lombok.Data;

@Data
public class MetodoPagoResponseDto {
    private Long idMetodoPago;
    private String nombre;
    private Boolean activo;
}