package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 100)
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "ESTUDIANTE_Carne")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "ROL_Id")
    private Rol rol;
}