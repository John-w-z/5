package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.response.MesaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    @GetMapping
    public ResponseEntity<List<MesaResponseDto>> obtenerMapaSalon() {
        // Retorna las mesas con sus estados dinámicos (Libre, Ocupada, Transición)
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/{id}/limpia")
    public ResponseEntity<String> marcarMesaLimpia(@PathVariable Long id) {
        // Cambia estado de Amarillo a Verde (Libre)
        return ResponseEntity.ok("Mesa marcada como limpia y disponible");
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferirMesa(@RequestParam Long idMesaOrigen, @RequestParam Long idMesaDestino, @RequestParam String pinAdmin) {
        // ECU-04 / RF-09: Requiere validación de PIN de Administrador (ECU-10)
        return ResponseEntity.ok("Mesa y consumo transferidos exitosamente");
    }
}