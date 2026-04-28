package com.Gestor.Proyecto.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "JUSTIFICACION")
public class Justificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime fechaPresentacion;

    @Column(length = 200)
    private String documentoRespaldo;

    @Column(length = 200)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ESTUDIANTE_Carne")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "MOTIVO_Id")
    private Motivo motivo;

    @ManyToOne
    @JoinColumn(name = "ESTADO_Id")
    private Estado estado;
}