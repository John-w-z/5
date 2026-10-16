package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class ComprobanteRequestDto {
    private Long idCuenta;
    private String tipoComprobante;
    private String serie;
    private String numero;
}