package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PedidoResponseDto {
    private Long idPedido;
    private Long idCuenta;
    private String usuarioNombre;
    private LocalDateTime fechaPedido;
    private String estado;
    private String observaciones;
}