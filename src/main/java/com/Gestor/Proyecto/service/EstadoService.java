package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Estado;
import com.Gestor.Proyecto.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public List<Estado> listar() { return estadoRepository.findAll(); }
    public Estado guardar(Estado estado) { return estadoRepository.save(estado); }
    public Estado actualizar(Integer id, Estado estado) {
        estado.setId(id);
        return estadoRepository.save(estado);
    }
    public void eliminar(Integer id) { estadoRepository.deleteById(id); }
}
