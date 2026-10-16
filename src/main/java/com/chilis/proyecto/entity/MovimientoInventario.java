package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "MOVIMIENTOS_INVENTARIO")
@Data
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tipo_movimiento", nullable = false, length = 30)
    private String tipoMovimiento; // 'INGRESO', 'SALIDA', 'MERMA', 'AJUSTE', 'CONSUMO_RECETA', 'SUSTITUCION'

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cantidad;

    @Column(name = "stock_anterior", precision = 12, scale = 2)
    private BigDecimal stockAnterior;

    @Column(name = "stock_nuevo", precision = 12, scale = 2)
    private BigDecimal stockNuevo;

    @Column(length = 255)
    private String motivo;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento = LocalDateTime.now();
}