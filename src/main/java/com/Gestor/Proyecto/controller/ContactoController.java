package com.Gestor.Proyecto.controller;

import com.Gestor.Proyecto.model.Contacto;
import com.Gestor.Proyecto.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactoController {
    private final ContactoService contactoService;

    @GetMapping("/estudiante/{carne}")
    public List<Contacto> listarPorEstudiante(@PathVariable Integer carne) {
        return contactoService.listarPorEstudiante(carne);
    }

    @PostMapping
    public Contacto guardar(@RequestBody Contacto contacto) { return contactoService.guardar(contacto); }

    @PutMapping("/{dpi}")
    public Contacto actualizar(@PathVariable Integer dpi, @RequestBody Contacto contacto) {
        contacto.setDpi(dpi);
        return contactoService.guardar(contacto);
    }

    @DeleteMapping("/{dpi}")
    public void eliminar(@PathVariable Integer dpi) { contactoService.eliminar(dpi); }
}