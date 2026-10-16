package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.PedidoRequestDto;
import com.chilis.proyecto.dto.response.PedidoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @PostMapping
    public ResponseEntity<PedidoResponseDto> enviarComandaACocina(@RequestBody PedidoRequestDto request) {
        // ECU-05 / RF-02 / RF-06: Envía comanda con notas especiales y verificación de stock
        return ResponseEntity.status(HttpStatus.CREATED).body(new PedidoResponseDto());
    }

    @DeleteMapping("/detalle/{idDetalle}")
    public ResponseEntity<String> eliminarPlatoBorrador(@PathVariable Long idDetalle) {
        // RF-05: Modificación de pedidos antes del envío
        return ResponseEntity.ok("Ítem removido del borrador local");
    }
}