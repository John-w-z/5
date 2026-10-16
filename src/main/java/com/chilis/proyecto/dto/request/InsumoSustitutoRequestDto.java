package com.chilis.proyecto.dto.request;

import lombok.Data;

@Data
public class InsumoSustitutoRequestDto {
    private Long idInsumo;
    private Long idInsumoSustituto;
    private String observacion;
}