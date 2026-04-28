package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Justificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JustificacionRepository extends JpaRepository<Justificacion, Integer> {
    List<Justificacion> findByEstudianteCarne(Integer carne);
}