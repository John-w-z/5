package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "INCIDENTES_COCINA")
@Data
public class IncidenteCocina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidente")
    private Long idIncidente;

    @ManyToOne
    @JoinColumn(name = "id_detalle")
    private DetallePedido detallePedido;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(name = "fecha_incidente", nullable = false)
    private LocalDateTime fechaIncidente = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 30)
    private String estado = "PENDIENTE"; // 'PENDIENTE', 'RESUELTO', 'CANCELADO'
}