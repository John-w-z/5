package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROPINAS_TURNO")
@Data
public class PropinaTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propina")
    private Long idPropina;

    @ManyToOne
    @JoinColumn(name = "id_turno", nullable = false)
    private TurnoCaja turnoCaja;

    @ManyToOne
    @JoinColumn(name = "id_usuario_mozo", nullable = false)
    private Usuario usuarioMozo;

    @Column(name = "monto_propina", nullable = false, precision = 12, scale = 4)
    private BigDecimal montoPropina;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}