package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "CUENTA_PROMOCION")
@Data
public class CuentaPromocion {

    @EmbeddedId
    private CuentaPromocionId id = new CuentaPromocionId();

    @ManyToOne
    @MapsId("idCuenta")
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @ManyToOne
    @MapsId("idPromocion")
    @JoinColumn(name = "id_promocion")
    private Promocion promocion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal descuento;

    @ManyToOne
    @JoinColumn(name = "id_usuario_autorizador")
    private Usuario usuarioAutorizador;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDateTime fechaAplicacion = LocalDateTime.now();

   @Embeddable
    @Data
    @EqualsAndHashCode
    public static class CuentaPromocionId implements Serializable {
        @Column(name = "id_cuenta")
        private Long idCuenta;

        @Column(name = "id_promocion")
        private Long idPromocion;

        public CuentaPromocionId() {}

        public CuentaPromocionId(Long idCuenta, Long idPromocion) {
            this.idCuenta = idCuenta;
            this.idPromocion = idPromocion;
        }
    }
}