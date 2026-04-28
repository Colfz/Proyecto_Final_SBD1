package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.TipoActividad;
import com.Gestor.Proyecto.repository.TipoActividadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoActividadService {
    private final TipoActividadRepository tipoActividadRepository;

    public List<TipoActividad> listar() { return tipoActividadRepository.findAll(); }
    public TipoActividad guardar(TipoActividad t) { return tipoActividadRepository.save(t); }
    public TipoActividad actualizar(Integer id, TipoActividad t) {
        t.setId(id);
        return tipoActividadRepository.save(t);
    }
    public void eliminar(Integer id) { tipoActividadRepository.deleteById(id); }
}