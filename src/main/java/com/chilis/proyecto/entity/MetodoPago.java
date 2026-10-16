package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "METODOS_PAGO")
@Data
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long idMetodoPago;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre; // 'EFECTIVO', 'TARJETA', 'YAPE', 'PLIN', 'TRANSFERENCIA'

    @Column(nullable = false)
    private Boolean activo = true;
}