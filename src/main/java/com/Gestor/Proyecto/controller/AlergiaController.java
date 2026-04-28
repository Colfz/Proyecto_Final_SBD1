package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.Alergia;
import com.Gestor.Proyecto.service.AlergiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alergias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AlergiaController {
    private final AlergiaService alergiaService;

    @GetMapping
    public List<Alergia> listar() { return alergiaService.listar(); }

    @PostMapping
    public Alergia guardar(@RequestBody Alergia a) { return alergiaService.guardar(a); }

    @PutMapping("/{id}")
    public Alergia actualizar(@PathVariable Integer id, @RequestBody Alergia a) {
        return alergiaService.actualizar(id, a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { alergiaService.eliminar(id); }
}