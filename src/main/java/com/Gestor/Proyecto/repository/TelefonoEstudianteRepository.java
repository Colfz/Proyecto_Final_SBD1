package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.TelefonoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TelefonoEstudianteRepository extends JpaRepository<TelefonoEstudiante, Integer> {
    List<TelefonoEstudiante> findByEstudianteCarne(Integer carne);
}