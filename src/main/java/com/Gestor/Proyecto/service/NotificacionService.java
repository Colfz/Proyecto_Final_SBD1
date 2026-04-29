package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Notificacion;
import com.Gestor.Proyecto.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;

    @Transactional
    public List<Notificacion> listarPorUsuario(Integer usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }
    @Transactional
    public List<Notificacion> listarNoLeidas(Integer usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeido(usuarioId, false);
    }
    public Notificacion marcarLeida(Integer id) {
        Notificacion n = notificacionRepository.findById(id).orElseThrow();
        n.setLeido(true);
        return notificacionRepository.save(n);
    }
}
