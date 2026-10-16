package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "LISTA_ESPERA")
@Data
public class ListaEspera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espera")
    private Long idEspecial;

    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombreCliente;

    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;

    @Column(name = "tiempo_estimado_min", nullable = false)
    private Integer tiempoEstimadoMin;

    @Column(name = "fecha_llegada", nullable = false)
    private LocalDateTime fechaLlegada = LocalDateTime.now();

    @Column(nullable = false, length = 30)
    private String estado = "EN_ESPERA"; // 'EN_ESPERA', 'ASIGNADO', 'CANCELADO'
}