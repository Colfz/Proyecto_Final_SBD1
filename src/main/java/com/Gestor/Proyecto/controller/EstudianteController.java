package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Estudiante;
import com.Gestor.Proyecto.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudianteController {
    private final EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> listar(
        @RequestParam(required = false) Integer carrera,
        @RequestParam(required = false) Boolean inscrito,
        @RequestParam(required = false) Boolean pensumCerrado
    ) {
        return estudianteService.filtrar(carrera, inscrito, pensumCerrado);
    }

    @GetMapping("/{carne}")
    public ResponseEntity<Estudiante> buscar(@PathVariable Integer carne) {
        return estudianteService.buscarPorId(carne)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) { return estudianteService.guardar(estudiante); }

    @PutMapping("/{carne}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer carne, @RequestBody Estudiante estudiante) {
        estudiante.setCarne(carne);
        return ResponseEntity.ok(estudianteService.guardar(estudiante));
    }

    @DeleteMapping("/{carne}")
    public void eliminar(@PathVariable Integer carne) { estudianteService.eliminar(carne); }
}