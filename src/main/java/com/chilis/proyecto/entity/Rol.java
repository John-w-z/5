package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROLES")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(nullable = false, unique = true, length = 20)
    private String nombre; // 'admin', 'server', 'cocinero', 'cajero'

    @Column(length = 200)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo = true;
}