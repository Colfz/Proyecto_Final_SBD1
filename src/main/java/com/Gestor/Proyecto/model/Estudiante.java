package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "ESTUDIANTE")
public class Estudiante {
    @Id
    private Integer carne;

    @Column(length = 45)
    private String cui;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    private LocalDateTime fechaNac;
    
    @Column(length = 20)
    private String genero;

    @Column(length = 100)
    private String fotografia;

    @Column(length = 100)
    private String correoInstitucional;

    @Column(length = 100)
    private String correoPersonal;

    @Column(length = 150)
    private String direccion;

    private LocalDateTime anioIngreso;

    private Boolean inscrito;

    private Boolean pensumCerrado;

    @ManyToOne
    @JoinColumn(name = "SANGRE_Id")
    private Sangre sangre;

    // Carreras (M:N)
    @ManyToMany
    @JoinTable(
        name = "PERTENECE",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "CARRERAS_Codigo")
    )
    private List<Carrera> carreras = new ArrayList<>();

    // Condiciones médicas (M:N)
    @ManyToMany
    @JoinTable(
        name = "ESTUDIANTE_CONDICION_MEDICA",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "CONDICION_MEDICA_Id")
    )
    private List<CondicionMedica> condicionesMedicas = new ArrayList<>();

    // Alergias (M:N)
    @ManyToMany
    @JoinTable(
        name = "ALERGIA_has_ESTUDIANTE",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "ALERGIA_Id")
    )
    private List<Alergia> alergias = new ArrayList<>();

    // Discapacidades (M:N)
    @ManyToMany
    @JoinTable(
        name = "ESTUDIANTE_has_DISCAPACIDAD",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "DISCAPACIDAD_Id")
    )
    private List<Discapacidad> discapacidades = new ArrayList<>();
}
