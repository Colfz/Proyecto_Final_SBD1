package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.*;
import com.Gestor.Proyecto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JustificacionService {
    private final JustificacionRepository justificacionRepository;
    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;

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

    public void eliminar(Integer id) { justificacionRepository.deleteById(id); }

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