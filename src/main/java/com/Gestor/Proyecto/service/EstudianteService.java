package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Estudiante;
import com.Gestor.Proyecto.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    
    @Transactional
    public List<Estudiante> listar() { return estudianteRepository.findAll(); }
    public Optional<Estudiante> buscarPorId(Integer carne) { return estudianteRepository.findById(carne); }
    public Estudiante guardar(Estudiante estudiante) { return estudianteRepository.save(estudiante); }
    public void eliminar(Integer carne) { estudianteRepository.deleteById(carne); }

    public List<Estudiante> filtrar(Integer codigoCarrera, Boolean inscrito, Boolean pensumCerrado) {
        if (codigoCarrera != null && inscrito != null) {
            return estudianteRepository.findByCarrerasCodigoAndInscrito(codigoCarrera, inscrito);
        } else if (codigoCarrera != null) {
            return estudianteRepository.findByCarrerasCodigo(codigoCarrera);
        } else if (inscrito != null) {
            return estudianteRepository.findByInscrito(inscrito);
        } else if (pensumCerrado != null) {
            return estudianteRepository.findByPensumCerrado(pensumCerrado);
        }
        return estudianteRepository.findAll();
    }
}