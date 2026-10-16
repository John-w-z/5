package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PagoRequestDto {
    private Long idCuenta;
    private Long idTurno;
    private Long idMetodoPago;
    private BigDecimal monto;
    private BigDecimal montoRecibido;
    private String referencia; // Para Yape/Plin/Tarjeta
}