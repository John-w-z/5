package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.ListaEsperaRequestDto;
import com.chilis.proyecto.dto.response.ListaEsperaResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lista-espera")
@CrossOrigin(origins = "*")
public class ListaEsperaController {

    @GetMapping
    public ResponseEntity<List<ListaEsperaResponseDto>> obtenerColaEspera() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    public ResponseEntity<String> registrarClienteCola(@RequestBody ListaEsperaRequestDto request) {
        // ECU-03 / RF-29: Registro con timestamp de llegada y cálculo predictivo de espera
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente agregado a la lista de espera digital");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> atenderOAbandonarCliente(@PathVariable Long id) {
        return ResponseEntity.ok("Registro de lista de espera actualizado");
    }
}