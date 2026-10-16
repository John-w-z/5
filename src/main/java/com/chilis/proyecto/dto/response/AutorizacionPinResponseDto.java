package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AutorizacionPinResponseDto {
    private Long idAutorizacion;
    private String solicitanteNombre;
    private String autorizadorNombre;
    private String operacion;
    private LocalDateTime fechaAutorizacion;
    private String resultado;
    private String observacion;
}