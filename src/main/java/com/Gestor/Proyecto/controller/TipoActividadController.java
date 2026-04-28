package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.TipoActividad;
import com.Gestor.Proyecto.service.TipoActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-actividad")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TipoActividadController {
    private final TipoActividadService tipoActividadService;

    @GetMapping
    public List<TipoActividad> listar() { return tipoActividadService.listar(); }

    @PostMapping
    public TipoActividad guardar(@RequestBody TipoActividad t) { return tipoActividadService.guardar(t); }

    @PutMapping("/{id}")
    public TipoActividad actualizar(@PathVariable Integer id, @RequestBody TipoActividad t) {
        return tipoActividadService.actualizar(id, t);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { tipoActividadService.eliminar(id); }
}