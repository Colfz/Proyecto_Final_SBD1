package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ACTIVIDAD_ACADEMICA")
public class ActividadAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String organizacion;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    @Column(length = 200)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "ESTUDIANTE_Carne")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "TIPO_ACTIVIDAD_Id")
    private TipoActividad tipoActividad;
}
