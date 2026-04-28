package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.TipoDiscapacidad;
import com.Gestor.Proyecto.repository.TipoDiscapacidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDiscapacidadService {
    private final TipoDiscapacidadRepository tipoDiscapacidadRepository;

    public List<TipoDiscapacidad> listar() { return tipoDiscapacidadRepository.findAll(); }
    public TipoDiscapacidad guardar(TipoDiscapacidad t) { return tipoDiscapacidadRepository.save(t); }
    public TipoDiscapacidad actualizar(Integer id, TipoDiscapacidad t) {
        t.setId(id);
        return tipoDiscapacidadRepository.save(t);
    }
    public void eliminar(Integer id) { tipoDiscapacidadRepository.deleteById(id); }
}