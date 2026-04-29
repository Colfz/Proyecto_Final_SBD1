package com.Gestor.Proyecto.controller;


import com.Gestor.Proyecto.model.Usuario;
import com.Gestor.Proyecto.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() { return usuarioService.listar(); }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) { return usuarioService.guardar(usuario); }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { usuarioService.eliminar(id); }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        return usuarioService.login(credenciales.get("nombre"), credenciales.get("contrasenia"))
            .map(u -> ResponseEntity.ok((Object) u))
            .orElse(ResponseEntity.status(401).body("Credenciales incorrectas"));
    }
}