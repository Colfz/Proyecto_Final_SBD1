package com.Gestor.Proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TELEFONOS_CONTACTO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TelefonoContacto {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Telefono", length = 50)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "CONTACTO_DPI")
    private Contacto contacto;
}