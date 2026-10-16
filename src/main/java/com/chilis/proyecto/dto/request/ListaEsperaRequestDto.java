package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class ListaEsperaRequestDto {
    private String nombreCliente;
    private Integer cantidadPersonas;
    private Integer tiempoEstimadoMin;
}