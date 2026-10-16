package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DETALLE_PEDIDO")
@Data
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(length = 500)
    private String comentarios;

    @Column(name = "estado_preparacion", nullable = false, length = 30)
    private String estadoPreparacion = "RECIBIDO"; // 'RECIBIDO', 'EN_PREPARACION', 'LISTO', 'RETIRADO', 'CANCELADO'

    @Column(name = "fecha_recibido", nullable = false)
    private LocalDateTime fechaRecibido = LocalDateTime.now();

    @Column(name = "fecha_inicio_preparacion")
    private LocalDateTime fechaInicioPreparacion;

    @Column(name = "fecha_listo")
    private LocalDateTime fechaListo;

    @Column(name = "fecha_retirado")
    private LocalDateTime fechaRetirado;

    @Column(name = "fecha_cancelado")
    private LocalDateTime fechaCancelado;
}