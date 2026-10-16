package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class AutorizacionPinRequestDto {
    private Long idUsuarioSolicitante;
    private String operacion;
    private String pinAutorizador;
}