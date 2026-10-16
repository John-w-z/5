package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PromocionRequestDto {
    private String nombre;
    private String descripcion;
    private String tipoDescuento; // PORCENTAJE o MONTO
    private BigDecimal valor;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}