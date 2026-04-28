package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.Motivo;
import com.Gestor.Proyecto.repository.MotivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MotivoService {
    private final MotivoRepository motivoRepository;

    public List<Motivo> listar() { return motivoRepository.findAll(); }
    public Motivo guardar(Motivo motivo) { return motivoRepository.save(motivo); }
    public Motivo actualizar(Integer id, Motivo motivo) {
        motivo.setId(id);
        return motivoRepository.save(motivo);
    }
    public void eliminar(Integer id) { motivoRepository.deleteById(id); }
}