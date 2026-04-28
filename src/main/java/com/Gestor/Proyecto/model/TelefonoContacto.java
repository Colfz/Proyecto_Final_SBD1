package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TELEFONOS_CONTACTO")
public class TelefonoContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "CONTACTO_DPI")
    private Contacto contacto;
}