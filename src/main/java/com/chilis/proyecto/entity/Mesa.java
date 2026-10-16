package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MESAS")
@Data
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private Long idMesa;

    @Column(name = "numero_mesa", nullable = false, unique = true)
    private Integer numeroMesa;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(length = 100)
    private String ubicacion;

    @Column(nullable = false)
    private Boolean activa = true;

    @Column(nullable = false, length = 30)
    private String estado = "LIBRE"; // 'LIBRE', 'OCUPADA', 'TRANSICION'
}