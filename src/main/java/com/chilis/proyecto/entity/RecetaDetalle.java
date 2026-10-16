package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "RECETA_DETALLE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_receta", "id_insumo"})
})
@Data
public class RecetaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta_detalle")
    private Long idRecetaDetalle;

    @ManyToOne
    @JoinColumn(name = "id_receta", nullable = false)
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;
}