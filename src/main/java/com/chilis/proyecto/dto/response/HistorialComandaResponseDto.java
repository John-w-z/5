package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistorialComandaResponseDto {
    private Long idHistorial;
    private Long idDetalle;
    private String estadoAnterior;
    private String estadoNuevo;
    private String usuarioNombre;
    private LocalDateTime fechaCambio;
    private String observacion;
}