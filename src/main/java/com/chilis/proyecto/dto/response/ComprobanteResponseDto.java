package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ComprobanteResponseDto {
    private Long idComprobante;
    private Long idCuenta;
    private String tipoComprobante;
    private String serie;
    private String numero;
    private LocalDateTime fechaEmision;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estadoSunat;
    private String codigoHash;
}