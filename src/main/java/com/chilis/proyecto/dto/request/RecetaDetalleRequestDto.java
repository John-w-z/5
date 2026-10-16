package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RecetaDetalleRequestDto {
    private Long idReceta;
    private Long idInsumo;
    private BigDecimal cantidad;
}