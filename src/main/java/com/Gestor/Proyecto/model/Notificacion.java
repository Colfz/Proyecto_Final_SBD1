package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NOTIFICACION")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String mensaje;

    private Boolean leido;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "USUARIO_Id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "JUSTIFICACION_Id")
    private Justificacion justificacion;
}