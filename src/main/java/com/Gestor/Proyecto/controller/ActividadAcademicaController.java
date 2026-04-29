package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.ActividadAcademica;
import com.Gestor.Proyecto.service.ActividadAcademicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/actividades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ActividadAcademicaController {
    private final ActividadAcademicaService actividadAcademicaService;

    @GetMapping
    public List<ActividadAcademica> listar() { return actividadAcademicaService.listar(); }

    @GetMapping("/estudiante/{carne}")
    public List<ActividadAcademica> listarPorEstudiante(@PathVariable Integer carne) {
        return actividadAcademicaService.listarPorEstudiante(carne);
    }

    @PostMapping
    public ActividadAcademica guardar(@RequestBody ActividadAcademica a) {
        return actividadAcademicaService.guardar(a);
    }

    @PutMapping("/{id}")
    public ActividadAcademica actualizar(@PathVariable Integer id, @RequestBody ActividadAcademica a) {
        a.setId(id);
        return actividadAcademicaService.guardar(a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { actividadAcademicaService.eliminar(id); }
}