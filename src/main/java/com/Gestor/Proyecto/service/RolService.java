package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Rol;
import com.Gestor.Proyecto.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository rolRepository;

    public List<Rol> listar() { return rolRepository.findAll(); }
    public Rol guardar(Rol rol) { return rolRepository.save(rol); }
    public Rol actualizar(Integer id, Rol rol) {
        rol.setId(id);
        return rolRepository.save(rol);
    }
    
    public void eliminar(Integer id) { rolRepository.deleteById(id); }
}