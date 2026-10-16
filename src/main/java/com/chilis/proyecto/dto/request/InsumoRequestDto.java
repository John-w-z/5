package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InsumoRequestDto {
    private String nombre;
    private String unidadMedida;
    private BigDecimal stockMinimo;
}