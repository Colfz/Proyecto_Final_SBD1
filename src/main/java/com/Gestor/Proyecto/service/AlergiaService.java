package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Alergia;
import com.Gestor.Proyecto.repository.AlergiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlergiaService {
    private final AlergiaRepository alergiaRepository;

    public List<Alergia> listar() { return alergiaRepository.findAll(); }
    public Alergia guardar(Alergia a) { return alergiaRepository.save(a); }
    public Alergia actualizar(Integer id, Alergia a) {
        a.setId(id);
        return alergiaRepository.save(a);
    }
    public void eliminar(Integer id) { alergiaRepository.deleteById(id); }
}