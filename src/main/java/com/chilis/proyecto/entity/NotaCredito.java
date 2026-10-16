package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTAS_CREDITO")
@Data
public class NotaCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_credito")
    private Long idNotaCredito;

    @ManyToOne
    @JoinColumn(name = "id_comprobante_origen", nullable = false)
    private Comprobante comprobanteOrigen;

    @ManyToOne
    @JoinColumn(name = "id_administrador", nullable = false)
    private Usuario administrador;

    @Column(name = "serie_correlativo", nullable = false, unique = true, length = 20)
    private String serieCorrelativo;

    @Column(name = "motivo_devolucion", nullable = false, columnDefinition = "TEXT")
    private String motivoDevolucion;

    @Column(name = "monto_devuelto", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoDevuelto;

    @Column(name = "estado_sunat", length = 30)
    private String estadoSunat = "ACEPTADO";

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision = LocalDateTime.now();
}