package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.LoginRequestDto;
import com.chilis.proyecto.dto.response.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    // Endpoint para el inicio de sesión de los 4 roles (Administrador, Mozo, Cocinero, Cajero)
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> autenticarUsuario(@RequestBody LoginRequestDto loginRequest) {
        // Aquí conectaremos posteriormente con el Service y Spring Security
        LoginResponseDto response = new LoginResponseDto();
        response.setUsuario(loginRequest.getUsuario());
        response.setToken("jwt-token-generado-provisional");
        response.setTipo("Bearer");
        response.setRol("ADMINISTRADOR"); // Ejemplo de rol devuelto
        response.setNombresCompletos("Alvaro Acosta");

        return ResponseEntity.ok(response);
    }
}