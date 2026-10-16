package com.chilis.proyecto.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MermaRequestDto {
    private Long idInsumo;
    private BigDecimal cantidad;
    private String motivo;
    private Long idUsuario;
}