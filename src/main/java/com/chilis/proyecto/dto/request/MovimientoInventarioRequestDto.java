package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MovimientoInventarioRequestDto {
    private Long idInsumo;
    private Long idUsuario;
    private String tipoMovimiento; // INGRESO, SALIDA, MERMA, etc.
    private BigDecimal cantidad;
    private String motivo;
}