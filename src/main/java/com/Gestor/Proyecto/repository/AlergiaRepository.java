package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlergiaRepository extends JpaRepository<Alergia, Integer> {
    
}