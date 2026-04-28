package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Sangre;
import com.Gestor.Proyecto.repository.SangreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SangreService {
    private final SangreRepository sangreRepository;

    public List<Sangre> listar() { return sangreRepository.findAll(); }
    public Sangre guardar(Sangre s) { return sangreRepository.save(s); }
    public Sangre actualizar(Integer id, Sangre s) {
        s.setId(id);
        return sangreRepository.save(s);
    }
    public void eliminar(Integer id) { sangreRepository.deleteById(id); }
}