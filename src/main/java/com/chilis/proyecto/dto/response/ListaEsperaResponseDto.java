package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ListaEsperaResponseDto {
    private Long idEspecial;
    private String nombreCliente;
    private Integer cantidadPersonas;
    private Integer tiempoEstimadoMin;
    private LocalDateTime fechaLlegada;
    private String estado;
}