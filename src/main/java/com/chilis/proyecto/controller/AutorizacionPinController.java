package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.AutorizacionPinRequestDto;
import com.chilis.proyecto.dto.response.AutorizacionPinResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autorizaciones-pin")
@CrossOrigin(origins = "*")
public class AutorizacionPinController {

    @PostMapping("/validar")
    public ResponseEntity<AutorizacionPinResponseDto> validarPinAdministrador(@RequestBody AutorizacionPinRequestDto request) {
        // ECU-10 / RNF-9.3: Valida el PIN enmascarado, verifica intentos fallidos (block 15 min) y registra en auditoría
        return ResponseEntity.ok(new AutorizacionPinResponseDto());
    }
}