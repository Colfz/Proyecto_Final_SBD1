package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.FechaAusencia;
import com.Gestor.Proyecto.repository.FechaAusenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FechaAusenciaService {
    private final FechaAusenciaRepository fechaAusenciaRepository;

    public List<FechaAusencia> listarPorJustificacion(Integer justificacionId) {
        return fechaAusenciaRepository.findByJustificacionId(justificacionId);
    }
    public FechaAusencia guardar(FechaAusencia f) { return fechaAusenciaRepository.save(f); }
    public void eliminar(Integer id) { fechaAusenciaRepository.deleteById(id); }
}