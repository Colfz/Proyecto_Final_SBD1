package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Notificacion;
import com.Gestor.Proyecto.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable Integer usuarioId) {
        return notificacionService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public List<Notificacion> listarNoLeidas(@PathVariable Integer usuarioId) {
        return notificacionService.listarNoLeidas(usuarioId);
    }

    @PutMapping("/{id}/leer")
    public Notificacion marcarLeida(@PathVariable Integer id) {
        return notificacionService.marcarLeida(id);
    }
}