package com.chilis.proyecto.dto.response;

import lombok.Data;

@Data
public class MesaResponseDto {
    private Long idMesa;
    private Integer numeroMesa;
    private Integer capacidad;
    private String ubicacion;
    private Boolean activa;
    private String estado;
}