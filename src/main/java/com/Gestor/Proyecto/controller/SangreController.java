package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Sangre;
import com.Gestor.Proyecto.service.SangreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sangres")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SangreController {
    private final SangreService sangreService;

    @GetMapping
    public List<Sangre> listar() { return sangreService.listar(); }

    @PostMapping
    public Sangre guardar(@RequestBody Sangre s) { return sangreService.guardar(s); }

    @PutMapping("/{id}")
    public Sangre actualizar(@PathVariable Integer id, @RequestBody Sangre s) {
        return sangreService.actualizar(id, s);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { sangreService.eliminar(id); }
}