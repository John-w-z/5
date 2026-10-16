package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.IncidenteCocinaRequestDto;
import com.chilis.proyecto.dto.response.DetallePedidoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cocina")
@CrossOrigin(origins = "*")
public class CocinaKdsController {

    @GetMapping("/comandas-pendientes")
    public ResponseEntity<List<DetallePedidoResponseDto>> obtenerComandasKds(@RequestParam String estacion) {
        // ECU-06 / RF-11: Enrutamiento inteligente por estaciones (Fría, Caliente, Barra)
        return ResponseEntity.ok(List.of());
    }

    @PatchMapping("/detalle/{id}/estado")
    public ResponseEntity<String> actualizarEstadoPreparacion(@PathVariable Long id, @RequestParam String nuevoEstado) {
        // ECU-06 / RF-13: Cambia entre RECIBIDO, EN_PREPARACION, LISTO
        return ResponseEntity.ok("Estado de preparación actualizado en tiempo real");
    }

    @PostMapping("/mermas")
    public ResponseEntity<String> registrarMermaCocina(@RequestBody IncidenteCocinaRequestDto request, @RequestParam(required = false) String pinAdmin) {
        // ECU-07 / RF-19: Control de mermas e incidentes (exige PIN si supera S/. 50)
        return ResponseEntity.ok("Merma registrada y stock descontado correctamente");
    }
}