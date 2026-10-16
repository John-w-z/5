package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDto {
    private Long idUsuario;
    private String usuario;
    private String rolNombre;
    private String nombresPersonal;
    private Boolean activo;
    private Integer intentosFallidos;
    private LocalDateTime fechaCreacion;
}
