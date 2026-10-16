package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.ProductoRequestDto;
import com.chilis.proyecto.dto.response.ProductoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

 
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuConfigController {

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponseDto>> listarMenu() {
        return ResponseEntity.ok(List.of());
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoResponseDto> actualizarPlatoYTemposAlerta(@PathVariable Long id, @RequestBody ProductoRequestDto request) {
        // ECU-02: Actualiza precios, imágenes y umbrales de semaforización (Amarilla/Roja)
        return ResponseEntity.ok(new ProductoResponseDto());
    }

    @PatchMapping("/productos/{id}/desactivar")
    public ResponseEntity<String> desactivarPlatoPorFaltaDeInsumos(@PathVariable Long id, @RequestParam String motivo) {
        // RF-18 / ECU-07: Desactivación preventiva de platos
        return ResponseEntity.ok("Plato desactivado de la carta digital correctamente");
    }
}