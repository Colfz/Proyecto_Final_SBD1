package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Carrera;
import com.Gestor.Proyecto.repository.CarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CarreraService {
    private final CarreraRepository carreraRepository;

    public List<Carrera> listar() { return carreraRepository.findAll(); }
    public Optional<Carrera> buscarPorId(Integer id) { return carreraRepository.findById(id); }
    public Carrera guardar(Carrera carrera) { return carreraRepository.save(carrera); }
    public void eliminar(Integer id) { carreraRepository.deleteById(id); }
}