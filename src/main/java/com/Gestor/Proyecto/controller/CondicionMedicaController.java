package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.CondicionMedica;
import com.Gestor.Proyecto.service.CondicionMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/condiciones-medicas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CondicionMedicaController {
    private final CondicionMedicaService condicionMedicaService;

    @GetMapping
    public List<CondicionMedica> listar() { return condicionMedicaService.listar(); }

    @PostMapping
    public CondicionMedica guardar(@RequestBody CondicionMedica c) { return condicionMedicaService.guardar(c); }

    @PutMapping("/{id}")
    public CondicionMedica actualizar(@PathVariable Integer id, @RequestBody CondicionMedica c) {
        return condicionMedicaService.actualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { condicionMedicaService.eliminar(id); }
}