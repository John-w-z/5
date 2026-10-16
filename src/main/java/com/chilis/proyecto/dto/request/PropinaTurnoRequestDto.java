package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PropinaTurnoRequestDto {
    private Long idTurno;
    private Long idUsuarioMozo;
    private BigDecimal montoPropina;
}