package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.NotaCreditoRequestDto;
import com.chilis.proyecto.dto.response.NotaCreditoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cierre-turno")
@CrossOrigin(origins = "*")
public class CierreCajaController {

    @PostMapping("/arqueo")
    public ResponseEntity<String> ejecutarArqueoYCierre(@RequestParam Long idTurno, @RequestParam Double efectivoContado, @RequestParam String pinAdmin) {
        // ECU-09 / RF-26: Arqueo, detección de diferencias y bloqueo transaccional post-cierre
        return ResponseEntity.ok("Turno de caja cerrado y reportes inalterables generados");
    }

    @PostMapping("/notas-credito")
    public ResponseEntity<NotaCreditoResponseDto> emitirNotaCreditoSunat(@RequestBody NotaCreditoRequestDto request) {
        // ECU-09 / RF-35: Anulación de boletas y envío de XML UBL 2.1 a SUNAT
        return ResponseEntity.ok(new NotaCreditoResponseDto());
    }
}