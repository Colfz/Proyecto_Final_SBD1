package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Estado;
import com.Gestor.Proyecto.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() { return estadoService.listar(); }

    @PostMapping
    public Estado guardar(@RequestBody Estado estado) { return estadoService.guardar(estado); }

    @PutMapping("/{id}")
    public Estado actualizar(@PathVariable Integer id, @RequestBody Estado estado) {
        return estadoService.actualizar(id, estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { estadoService.eliminar(id); }
}
