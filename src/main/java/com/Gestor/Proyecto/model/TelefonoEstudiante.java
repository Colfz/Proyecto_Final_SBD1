package com.Gestor.Proyecto.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TELEFONOS_ESTUDIANTE")
public class TelefonoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "ESTUDIANTE_Carne")
    private Estudiante estudiante;
}