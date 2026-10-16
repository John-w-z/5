package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HistorialPrecioResponseDto {
    private Long idHistorialPrecio;
    private Long idProducto;
    private BigDecimal precioAnterior;
    private BigDecimal precioNuevo;
    private String usuarioCambia;
    private LocalDateTime fechaCambio;
    private String motivo;
}