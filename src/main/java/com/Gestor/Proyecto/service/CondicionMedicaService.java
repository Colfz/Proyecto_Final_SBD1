package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.CondicionMedica;
import com.Gestor.Proyecto.repository.CondicionMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CondicionMedicaService {
    private final CondicionMedicaRepository condicionMedicaRepository;

    public List<CondicionMedica> listar() { return condicionMedicaRepository.findAll(); }
    public CondicionMedica guardar(CondicionMedica c) { return condicionMedicaRepository.save(c); }
    public CondicionMedica actualizar(Integer id, CondicionMedica c) {
        c.setId(id);
        return condicionMedicaRepository.save(c);
    }
    public void eliminar(Integer id) { condicionMedicaRepository.deleteById(id); }
}