package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Motivo;
import com.Gestor.Proyecto.service.MotivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/motivos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MotivoController {
    private final MotivoService motivoService;

    @GetMapping
    public List<Motivo> listar() { return motivoService.listar(); }

    @PostMapping
    public Motivo guardar(@RequestBody Motivo motivo) { return motivoService.guardar(motivo); }

    @PutMapping("/{id}")
    public Motivo actualizar(@PathVariable Integer id, @RequestBody Motivo motivo) {
        return motivoService.actualizar(id, motivo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { motivoService.eliminar(id); }
}
