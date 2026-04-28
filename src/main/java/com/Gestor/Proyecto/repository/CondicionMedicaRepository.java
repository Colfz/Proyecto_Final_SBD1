package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.CondicionMedica;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CondicionMedicaRepository extends JpaRepository<CondicionMedica, Integer> {
    
}