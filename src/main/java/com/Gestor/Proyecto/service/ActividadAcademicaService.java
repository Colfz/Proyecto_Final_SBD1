package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.ActividadAcademica;
import com.Gestor.Proyecto.repository.ActividadAcademicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ActividadAcademicaService {
    private final ActividadAcademicaRepository actividadAcademicaRepository;

    public List<ActividadAcademica> listar() { return actividadAcademicaRepository.findAll(); }
    public List<ActividadAcademica> listarPorEstudiante(Integer carne) {
        return actividadAcademicaRepository.findByEstudianteCarne(carne);
    }
    public Optional<ActividadAcademica> buscarPorId(Integer id) { return actividadAcademicaRepository.findById(id); }
    public ActividadAcademica guardar(ActividadAcademica a) { return actividadAcademicaRepository.save(a); }
    public void eliminar(Integer id) { actividadAcademicaRepository.deleteById(id); }
}