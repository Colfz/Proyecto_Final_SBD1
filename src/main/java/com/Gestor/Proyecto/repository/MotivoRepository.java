package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MotivoRepository extends JpaRepository<Motivo, Integer> {
    
}
