package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    List<Contacto> findByEstudianteCarne(Integer carne);
}
