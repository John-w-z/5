package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "PEDIDOS")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column(nullable = false, length = 30)
    private String estado = "PENDIENTE"; // 'PENDIENTE', 'ENVIADO', 'EN_PREPARACION', 'PARCIALMENTE_LISTO', 'LISTO', 'ENTREGADO', 'CANCELADO'

    @Column(length = 500)
    private String observaciones;
}