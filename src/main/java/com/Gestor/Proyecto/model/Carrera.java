package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CARRERAS")
public class Carrera {
    @Id
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 100)
    private String coordinador;
}
