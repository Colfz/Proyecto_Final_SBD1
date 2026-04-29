package com.Gestor.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTACTO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contacto {
    @Id
    @Column(name= "DPI")
    private Integer dpi;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "Parentesco", length = 50)
    private String parentesco;

    @Column(name = "Direccion", length = 150)
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTUDIANTE_Carne")
    @JsonIgnoreProperties({"carreras", "condicionesMedicas", "alergias", "discapacidades"})
    private Estudiante estudiante;
}
