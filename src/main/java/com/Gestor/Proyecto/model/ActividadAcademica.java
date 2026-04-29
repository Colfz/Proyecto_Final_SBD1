package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "ACTIVIDAD_ACADEMICA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ActividadAcademica {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name="Organizacion",length = 100)
    private String organizacion;

    @Column(name = "Fecha_Inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "Fecha_Fin")
    private LocalDateTime fechaFin;

    @Column(name = "Observacion", length = 200)
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTUDIANTE_Carne")
    @JsonIgnoreProperties({"carreras", "condicionesMedicas", "alergias", "discapacidades"})
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_ACTIVIDAD_Id")
    private TipoActividad tipoActividad;
}
