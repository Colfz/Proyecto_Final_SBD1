package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Carrera;
import com.Gestor.Proyecto.service.CarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CarreraController {
    private final CarreraService carreraService;

    @GetMapping
    public List<Carrera> listar() { return carreraService.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> buscar(@PathVariable Integer id) {
        return carreraService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrera guardar(@RequestBody Carrera carrera) { return carreraService.guardar(carrera); }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizar(@PathVariable Integer id, @RequestBody Carrera carrera) {
        carrera.setCodigo(id);
        return ResponseEntity.ok(carreraService.guardar(carrera));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { carreraService.eliminar(id); }
}
