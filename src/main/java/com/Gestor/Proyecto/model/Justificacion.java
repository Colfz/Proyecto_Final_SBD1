package com.Gestor.Proyecto.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "JUSTIFICACION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Justificacion {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Fecha_Presentacion")
    private LocalDateTime fechaPresentacion;

    @Column(name = "Documento_Respaldo", length = 200)
    private String documentoRespaldo;

    @Column(name = "Descripcion", length = 200)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTUDIANTE_Carne")
    @JsonIgnoreProperties({"carreras", "condicionesMedicas", "alergias", "discapacidades"})
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MOTIVO_Id")
    private Motivo motivo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTADO_Id")
    private Estado estado;
}