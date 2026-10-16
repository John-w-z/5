package com.chilis.proyecto.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "INSUMOS_SUSTITUTOS")
@Data
public class InsumoSustituto {

    @EmbeddedId
    private InsumoSustitutoId id = new InsumoSustitutoId();

    @ManyToOne
    @MapsId("idInsumo")
    @JoinColumn(name = "id_insumo")
    private Insumo insumoOriginal;

    @ManyToOne
    @MapsId("idInsumoSustituto")
    @JoinColumn(name = "id_insumo_sustituto")
    private Insumo insumoSustituto;

    @Column(length = 255)
    private String observacion;

   @Embeddable
    @Data
    @EqualsAndHashCode
    public static class InsumoSustitutoId implements Serializable {
        @Column(name = "id_insumo")
        private Long idInsumo;

        @Column(name = "id_insumo_sustituto")
        private Long idInsumoSustituto;
        
        // Constructor vacío explícito
        public InsumoSustitutoId() {}

        // Constructor con parámetros por si lo necesitas
        public InsumoSustitutoId(Long idInsumo, Long idInsumoSustituto) {
            this.idInsumo = idInsumo;
            this.idInsumoSustituto = idInsumoSustituto;
        }
    }
}