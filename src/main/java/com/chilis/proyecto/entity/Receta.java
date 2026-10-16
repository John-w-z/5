package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "RECETAS")
@Data
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Long idReceta;

    @OneToOne
    @JoinColumn(name = "id_producto", nullable = false, unique = true)
    private Producto producto;

    @Column(columnDefinition = "TEXT")
    private String instrucciones;
}