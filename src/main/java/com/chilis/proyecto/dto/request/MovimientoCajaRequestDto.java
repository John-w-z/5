package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MovimientoCajaRequestDto {
    private Long idTurno;
    private String tipoMovimiento; // INGRESO o EGRESO
    private BigDecimal monto;
    private String descripcion;
}