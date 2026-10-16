package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class DetallePedidoRequestDto {
    private Long idPedido;
    private Long idProducto;
    private Integer cantidad;
    private String comentarios;
}