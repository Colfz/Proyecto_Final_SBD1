package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Usuario;
import com.Gestor.Proyecto.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listar() { return usuarioRepository.findAll(); }
    public Optional<Usuario> buscarPorId(Integer id) { return usuarioRepository.findById(id); }
    public Optional<Usuario> buscarPorNombre(String nombre) { return usuarioRepository.findByNombre(nombre); }
    public Usuario guardar(Usuario usuario) { return usuarioRepository.save(usuario); }
    public void eliminar(Integer id) { usuarioRepository.deleteById(id); }

    public Optional<Usuario> login(String nombre, String contrasenia) {
        return usuarioRepository.findByNombre(nombre)
            .filter(u -> u.getContrasenia().equals(contrasenia));
    }
}