package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.FechaAusencia;
import com.Gestor.Proyecto.service.FechaAusenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fecha-ausencia")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FechaAusenciaController {
    private final FechaAusenciaService fechaAusenciaService;

    @GetMapping("/justificacion/{id}")
    public List<FechaAusencia> listarPorJustificacion(@PathVariable Integer id) {
        return fechaAusenciaService.listarPorJustificacion(id);
    }

    @PostMapping
    public FechaAusencia guardar(@RequestBody FechaAusencia f) {
        return fechaAusenciaService.guardar(f);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        fechaAusenciaService.eliminar(id);
    }
}