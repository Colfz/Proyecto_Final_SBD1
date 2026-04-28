package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Discapacidad;
import com.Gestor.Proyecto.service.DiscapacidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discapacidades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DiscapacidadController {
    private final DiscapacidadService discapacidadService;

    @GetMapping
    public List<Discapacidad> listar() { return discapacidadService.listar(); }

    @PostMapping
    public Discapacidad guardar(@RequestBody Discapacidad d) { return discapacidadService.guardar(d); }

    @PutMapping("/{id}")
    public Discapacidad actualizar(@PathVariable Integer id, @RequestBody Discapacidad d) {
        return discapacidadService.actualizar(id, d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { discapacidadService.eliminar(id); }
}