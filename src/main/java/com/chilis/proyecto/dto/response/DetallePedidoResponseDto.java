package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DetallePedidoResponseDto {
    private Long idDetalle;
    private String productoNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String comentarios;
    private String estadoPreparacion;
    private LocalDateTime fechaRecibido;
}
