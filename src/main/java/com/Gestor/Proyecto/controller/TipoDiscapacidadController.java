package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.TipoDiscapacidad;
import com.Gestor.Proyecto.service.TipoDiscapacidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-discapacidad")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TipoDiscapacidadController {
    private final TipoDiscapacidadService tipoDiscapacidadService;

    @GetMapping
    public List<TipoDiscapacidad> listar() { return tipoDiscapacidadService.listar(); }

    @PostMapping
    public TipoDiscapacidad guardar(@RequestBody TipoDiscapacidad t) { return tipoDiscapacidadService.guardar(t); }

    @PutMapping("/{id}")
    public TipoDiscapacidad actualizar(@PathVariable Integer id, @RequestBody TipoDiscapacidad t) {
        return tipoDiscapacidadService.actualizar(id, t);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { tipoDiscapacidadService.eliminar(id); }
}