package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.TelefonoContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TelefonoContactoRepository extends JpaRepository<TelefonoContacto, Integer> {
    List<TelefonoContacto> findByContactoDpi(Integer dpi);
}