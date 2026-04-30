package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.*;
import com.Gestor.Proyecto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JustificacionService {
    private final JustificacionRepository justificacionRepository;
    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final FechaAusenciaRepository fechaAusenciaRepository;
    
    @Transactional
    public List<Justificacion> listar() { return justificacionRepository.findAll(); }
    public List<Justificacion> listarPorEstudiante(Integer carne) {
        return justificacionRepository.findByEstudianteCarne(carne);
    }
    public Optional<Justificacion> buscarPorId(Integer id) { return justificacionRepository.findById(id); }

    public Justificacion guardar(Justificacion justificacion) {
        Justificacion guardada = justificacionRepository.save(justificacion);
        // Generar notificaciones al guardar una nueva justificación
        generarNotificaciones(guardada);
        return guardada;
    }

    public Justificacion actualizarEstado(Integer id, Estado estado) {
        Justificacion j = justificacionRepository.findById(id).orElseThrow();
        j.setEstado(estado);
        return justificacionRepository.save(j);
    }

    @Transactional
    public void eliminar(Integer id) {
        // 1. Eliminar notificaciones asociadas
        notificacionRepository.findAll().stream()
            .filter(n -> n.getJustificacion() != null && n.getJustificacion().getId().equals(id))
            .forEach(n -> notificacionRepository.deleteById(n.getId()));

        // 2. Eliminar fechas de ausencia asociadas
        fechaAusenciaRepository.findByJustificacionId(id)
            .forEach(f -> fechaAusenciaRepository.deleteById(f.getId()));

        // 3. Eliminar la justificación
        justificacionRepository.deleteById(id);
    }

    private void generarNotificaciones(Justificacion justificacion) {
        // Notificar a coordinadores y admins
        List<Usuario> destinatarios = usuarioRepository.findAll().stream()
            .filter(u -> u.getRol().getTipo().equals("COORDINADOR") || u.getRol().getTipo().equals("ADMIN"))
            .toList();

        for (Usuario usuario : destinatarios) {
            Notificacion notificacion = new Notificacion();
            notificacion.setMensaje("Nueva justificación presentada por estudiante con carné: "
                + justificacion.getEstudiante().getCarne());
            notificacion.setLeido(false);
            notificacion.setFecha(LocalDateTime.now());
            notificacion.setUsuario(usuario);
            notificacion.setJustificacion(justificacion);
            notificacionRepository.save(notificacion);
        }
    }
}