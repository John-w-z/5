package com.chilis.proyecto.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CuentaResponseDto {
    private Long idCuenta;
    private Integer numeroMesa;
    private String clienteNombre;
    private String usuarioNombre;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private String estado;
    private BigDecimal total;
}