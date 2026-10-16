package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class NotaCreditoResponseDto {
    private Long idNotaCredito;
    private String serieComprobanteOrigen;
    private String administradorNombre;
    private String serieCorrelativo;
    private String motivoDevolucion;
    private BigDecimal montoDevuelto;
    private String estadoSunat;
    private LocalDateTime fechaEmision;
}