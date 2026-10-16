package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class NotaCreditoRequestDto {
    private Long idComprobanteOrigen;
    private Long idAdministrador;
    private String serieCorrelativo;
    private String motivoDevolucion;
    private BigDecimal montoDevuelto;
}