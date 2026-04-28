package com.Gestor.Proyecto.repository;

import com.Gestor.Proyecto.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuarioId(Integer usuarioId);
    List<Notificacion> findByUsuarioIdAndLeido(Integer usuarioId, Boolean leido);
}