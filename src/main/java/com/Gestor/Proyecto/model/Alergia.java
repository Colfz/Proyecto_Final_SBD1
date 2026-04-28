package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ALERGIA")
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String tipo;
}
