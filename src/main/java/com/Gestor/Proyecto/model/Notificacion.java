package com.Gestor.Proyecto.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "NOTIFICACION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notificacion {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Mensaje", length = 200)
    private String mensaje;

    @Column(name = "Leido")
    private Boolean leido;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USUARIO_Id")
    @JsonIgnoreProperties({"estudiante"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUSTIFICACION_Id")
    @JsonIgnoreProperties({"estudiante", "motivo", "estado"})
    private Justificacion justificacion;
}