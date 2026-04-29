package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "ESTUDIANTE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estudiante {
    @Id
    @Column(name = "Carne")
    private Integer carne;

    @Column(name = "Cui",length = 45)
    private String cui;

    @Column(name = "Nombres",length = 50)
    private String nombres;

    @Column(name = "Apellidos",length = 50)
    private String apellidos;

    @Column(name = "Fecha_Nac")
    private LocalDateTime fechaNac;
    
    @Column(name = "Genero",length = 20)
    private String genero;

    @Column(name = "Fotografia",length = 100)
    private String fotografia;

    @Column(name = "Correo_Institucional",length = 100)
    private String correoInstitucional;

    @Column(name = "Correo_Personal",length = 100)
    private String correoPersonal;

    @Column(name = "Direccion",length = 150)
    private String direccion;

    @Column(name = "Anio_Ingreso")
    private LocalDateTime anioIngreso;

    @Column(name = "Inscrito")
    private Boolean inscrito;

    @Column(name = "PensumCerrado")
    private Boolean pensumCerrado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SANGRE_Id")
    private Sangre sangre;

    // Carreras (M:N)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "PERTENECE",
    joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
    inverseJoinColumns = @JoinColumn(name = "CARRERAS_Codigo")
    )
    private List<Carrera> carreras = new ArrayList<>();

    // Condiciones médicas (M:N)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ESTUDIANTE_CONDICION_MEDICA",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "CONDICION_MEDICA_Id")
    )
    private List<CondicionMedica> condicionesMedicas = new ArrayList<>();

    // Alergias (M:N)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ALERGIA_has_ESTUDIANTE",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "ALERGIA_Id")
    )
    private List<Alergia> alergias = new ArrayList<>();

    // Discapacidades (M:N)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ESTUDIANTE_has_DISCAPACIDAD",
        joinColumns = @JoinColumn(name = "ESTUDIANTE_Carne"),
        inverseJoinColumns = @JoinColumn(name = "DISCAPACIDAD_Id")
    )
    private List<Discapacidad> discapacidades = new ArrayList<>();
}
