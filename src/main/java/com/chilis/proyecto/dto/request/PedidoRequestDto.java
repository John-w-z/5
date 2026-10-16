package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class PedidoRequestDto {
    private Long idCuenta;
    private Long idUsuario;
    private String observaciones;
}