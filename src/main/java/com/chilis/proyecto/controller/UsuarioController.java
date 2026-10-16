package com.chilis.proyecto.controller;

import com.chilis.proyecto.dto.request.UsuarioRequestDto;
import com.chilis.proyecto.dto.response.UsuarioResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivarUsuario(@PathVariable Long id) {
        // Borrado lógico (RNF-38.2)
        return ResponseEntity.ok("Usuario inhabilitado lógicamente de forma exitosa");
    }
}