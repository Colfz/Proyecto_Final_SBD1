package com.Gestor.Proyecto.service;


import com.Gestor.Proyecto.model.TelefonoContacto;
import com.Gestor.Proyecto.repository.TelefonoContactoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefonoContactoService {
    private final TelefonoContactoRepository telefonoContactoRepository;

    public List<TelefonoContacto> listarPorContacto(Integer dpi) {
        return telefonoContactoRepository.findByContactoDpi(dpi);
    }
    public TelefonoContacto guardar(TelefonoContacto t) { return telefonoContactoRepository.save(t); }
    public void eliminar(Integer id) { telefonoContactoRepository.deleteById(id); }
}