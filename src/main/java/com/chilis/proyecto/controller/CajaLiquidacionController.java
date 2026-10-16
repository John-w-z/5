package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.PagoRequestDto;
import com.chilis.proyecto.dto.response.ComprobanteResponseDto;
import com.chilis.proyecto.dto.response.CuentaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caja")
@CrossOrigin(origins = "*")
public class CajaLiquidacionController {

    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<CuentaResponseDto> sincronizarCuentaMesa(@PathVariable Long idCuenta) {
        // ECU-08 / RF-20: Sincronización del consumo por mesa con caja
        return ResponseEntity.ok(new CuentaResponseDto());
    }

    @PostMapping("/pagar")
    public ResponseEntity<ComprobanteResponseDto> procesarPagoYEmitirBoleta(@RequestBody PagoRequestDto request) {
        // ECU-08 / RF-24 / RF-36: Múltiples pagos (Efectivo/Tarjeta/QR) y emisión UBL 2.1 SUNAT
        return ResponseEntity.ok(new ComprobanteResponseDto());
    }
}