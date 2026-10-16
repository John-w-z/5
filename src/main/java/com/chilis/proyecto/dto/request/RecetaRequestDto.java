package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class RecetaRequestDto {
    private Long idProducto;
    private String instrucciones;
}