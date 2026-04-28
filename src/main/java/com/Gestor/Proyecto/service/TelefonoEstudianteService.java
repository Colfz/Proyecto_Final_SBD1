package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.TelefonoEstudiante;
import com.Gestor.Proyecto.repository.TelefonoEstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefonoEstudianteService {
    private final TelefonoEstudianteRepository telefonoEstudianteRepository;

    public List<TelefonoEstudiante> listarPorEstudiante(Integer carne) {
        return telefonoEstudianteRepository.findByEstudianteCarne(carne);
    }
    public TelefonoEstudiante guardar(TelefonoEstudiante t) { return telefonoEstudianteRepository.save(t); }
    public void eliminar(Integer id) { telefonoEstudianteRepository.deleteById(id); }
}