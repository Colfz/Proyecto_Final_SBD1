package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.*;
import com.Gestor.Proyecto.service.JustificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/justificaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JustificacionController {
    private final JustificacionService justificacionService;

    @GetMapping
    public List<Justificacion> listar() { return justificacionService.listar(); }

    @GetMapping("/estudiante/{carne}")
    public List<Justificacion> listarPorEstudiante(@PathVariable Integer carne) {
        return justificacionService.listarPorEstudiante(carne);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justificacion> buscar(@PathVariable Integer id) {
        return justificacionService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Justificacion guardar(@RequestBody Justificacion justificacion) {
        return justificacionService.guardar(justificacion);
    }

    @PutMapping("/{id}/estado")
    public Justificacion actualizarEstado(@PathVariable Integer id, @RequestBody Estado estado) {
        return justificacionService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { justificacionService.eliminar(id); }
}