package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Sangre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SangreRepository extends JpaRepository<Sangre, Integer> {
    
}