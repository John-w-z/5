package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CuentaPromocionResponseDto {
    private Long idCuenta;
    private Long idPromocion;
    private String promocionNombre;
    private BigDecimal descuento;
    private String usuarioAutorizador;
    private LocalDateTime fechaAplicacion;
}