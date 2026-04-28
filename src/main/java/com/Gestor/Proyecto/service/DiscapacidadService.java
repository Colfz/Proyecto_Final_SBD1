package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Discapacidad;
import com.Gestor.Proyecto.repository.DiscapacidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscapacidadService {
    private final DiscapacidadRepository discapacidadRepository;

    public List<Discapacidad> listar() { return discapacidadRepository.findAll(); }
    public Discapacidad guardar(Discapacidad d) { return discapacidadRepository.save(d); }
    public Discapacidad actualizar(Integer id, Discapacidad d) {
        d.setId(id);
        return discapacidadRepository.save(d);
    }
    public void eliminar(Integer id) { discapacidadRepository.deleteById(id); }
}
