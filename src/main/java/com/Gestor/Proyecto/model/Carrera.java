package com.Gestor.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CARRERAS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrera {
    @Id
    @Column(name = "Codigo")
    private Integer codigo;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name= "Coordinador", length = 100)
    private String coordinador;
}
