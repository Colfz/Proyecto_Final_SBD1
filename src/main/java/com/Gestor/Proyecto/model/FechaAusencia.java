package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "FECHA_AUSENCIA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FechaAusencia {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Fecha_Ausencia")
    private LocalDateTime fechaAusencia;

    @ManyToOne
    @JoinColumn(name = "JUSTIFICACION_Id")
    private Justificacion justificacion;
}