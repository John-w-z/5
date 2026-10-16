package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoResponseDto {
    private Long idProducto;
    private String categoriaNombre;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean disponible;
    private Boolean activo;
    private Integer tiempoAlertaAmarilla;
    private Integer tiempoAlertaRoja;
}