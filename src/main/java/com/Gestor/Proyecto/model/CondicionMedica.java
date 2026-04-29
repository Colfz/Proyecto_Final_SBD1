package com.Gestor.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONDICION_MEDICA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CondicionMedica {
    @Id
    @Column(name= "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;
}
