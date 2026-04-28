package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Contacto;
import com.Gestor.Proyecto.repository.ContactoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContactoService {
    private final ContactoRepository contactoRepository;

    public List<Contacto> listar() { return contactoRepository.findAll(); }
    public List<Contacto> listarPorEstudiante(Integer carne) { return contactoRepository.findByEstudianteCarne(carne); }
    public Optional<Contacto> buscarPorId(Integer dpi) { return contactoRepository.findById(dpi); }
    public Contacto guardar(Contacto contacto) { return contactoRepository.save(contacto); }
    public void eliminar(Integer dpi) { contactoRepository.deleteById(dpi); }
}
