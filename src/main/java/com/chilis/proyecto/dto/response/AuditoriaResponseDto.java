package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditoriaResponseDto {
    private Long idAuditoria;
    private String usuarioNombre;
    private String accion;
    private String tablaAfectada;
    private Integer registroId;
    private String descripcion;
    private LocalDateTime fecha;
}