package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class IncidenteCocinaRequestDto {
    private Long idDetalle;
    private String descripcion;
    private Long idUsuario;
}