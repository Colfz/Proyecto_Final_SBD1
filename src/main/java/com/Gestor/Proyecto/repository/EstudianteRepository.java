package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    List<Estudiante> findByCarrerasCodigoAndInscrito(Integer codigoCarrera, Boolean inscrito);
    List<Estudiante> findByCarrerasCodigo(Integer codigoCarrera);
    List<Estudiante> findByInscrito(Boolean inscrito);
    List<Estudiante> findByPensumCerrado(Boolean pensumCerrado);
}
