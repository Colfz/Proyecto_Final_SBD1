package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "FECHA_AUSENCIA")
public class FechaAusencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaAusencia;

    @ManyToOne
    @JoinColumn(name = "JUSTIFICACION_Id")
    private Justificacion justificacion;
}