package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ROL")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String tipo;
    
}
