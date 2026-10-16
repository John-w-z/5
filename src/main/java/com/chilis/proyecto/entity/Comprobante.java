package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMPROBANTES", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"serie", "numero"})
})
@Data
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Long idComprobante;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;

    @Column(name = "tipo_comprobante", nullable = false, length = 30)
    private String tipoComprobante;

    @Column(nullable = false, length = 10)
    private String serie;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal impuesto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "estado_sunat", length = 30)
    private String estadoSunat;

    @Column(name = "codigo_hash", length = 80)
    private String codigoHash;

    @Column(name = "enlace_encuesta", length = 500)
    private String enlaceEncuesta;

    @Column(name = "xml_sunat_url", length = 255)
    private String xmlSunatUrl;
}