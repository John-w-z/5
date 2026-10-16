package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoRequestDto {
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer tiempoAlertaAmarilla;
    private Integer tiempoAlertaRoja;
}