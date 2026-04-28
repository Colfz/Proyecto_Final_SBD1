package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.ActividadAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ActividadAcademicaRepository extends JpaRepository<ActividadAcademica, Integer> {
    List<ActividadAcademica> findByEstudianteCarne(Integer carne);
}