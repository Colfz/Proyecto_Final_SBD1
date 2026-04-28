package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CONTACTO")
public class Contacto {
    @Id
    private Integer dpi;

    @Column(length = 100)
    private String nombre;

    @Column(length = 50)
    private String parentesco;

    @Column(length = 150)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "ESTUDIANTE_Carne")
    private Estudiante estudiante;
}
