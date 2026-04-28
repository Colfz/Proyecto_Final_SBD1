package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DISCAPACIDAD")
public class Discapacidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "TIPO_DISCAPACIDAD_Id")
    private TipoDiscapacidad tipoDiscapacidad;
}