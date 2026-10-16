package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class MesaRequestDto {
    private Integer numeroMesa;
    private Integer capacidad;
    private String ubicacion;
}