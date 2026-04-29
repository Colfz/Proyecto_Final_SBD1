package com.Gestor.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DISCAPACIDAD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Discapacidad {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Tipo", length = 50)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "TIPO_DISCAPACIDAD_Id")
    private TipoDiscapacidad tipoDiscapacidad;
}