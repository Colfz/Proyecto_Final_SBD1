package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Rol;
import com.Gestor.Proyecto.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping
    public List<Rol> listar() { return rolService.listar(); }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) { return rolService.guardar(rol); }

    @PutMapping("/{id}")
    public Rol actualizar(@PathVariable Integer id, @RequestBody Rol rol) {
        return rolService.actualizar(id, rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { rolService.eliminar(id); }
}
