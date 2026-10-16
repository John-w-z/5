package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TurnoCajaRequestDto {
    private Long idUsuario;
    private BigDecimal montoInicial;
}