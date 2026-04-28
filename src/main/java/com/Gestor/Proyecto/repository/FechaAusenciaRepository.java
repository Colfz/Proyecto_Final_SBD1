package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.FechaAusencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FechaAusenciaRepository extends JpaRepository<FechaAusencia, Integer> {
    List<FechaAusencia> findByJustificacionId(Integer justificacionId);
}