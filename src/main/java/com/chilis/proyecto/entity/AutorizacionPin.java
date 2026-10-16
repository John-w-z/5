package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUTORIZACIONES_PIN")
@Data
public class AutorizacionPin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorizacion")
    private Long idAutorizacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario_solicitante", nullable = false)
    private Usuario usuarioSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_usuario_autorizador")
    private Usuario usuarioAutorizador;

    @Column(nullable = false, length = 150)
    private String operacion;

    @Column(name = "fecha_autorizacion", nullable = false)
    private LocalDateTime fechaAutorizacion = LocalDateTime.now();

    @Column(nullable = false, length = 30)
    private String resultado; // 'EXITOSO', 'DENEGADO'

    @Column(length = 500)
    private String observacion;
}