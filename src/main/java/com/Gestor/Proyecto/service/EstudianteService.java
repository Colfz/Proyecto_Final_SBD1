package com.Gestor.Proyecto.service;

import com.Gestor.Proyecto.model.Estudiante;
import com.Gestor.Proyecto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final ActividadAcademicaRepository actividadAcademicaRepository;
    private final ContactoRepository contactoRepository;
    private final TelefonoEstudianteRepository telefonoEstudianteRepository;
    private final TelefonoContactoRepository telefonoContactoRepository;
    private final JustificacionRepository justificacionRepository;
    private final NotificacionRepository notificacionRepository;
    private final FechaAusenciaRepository fechaAusenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Estudiante> listar() { return estudianteRepository.findAll(); }
    public Optional<Estudiante> buscarPorId(Integer carne) { return estudianteRepository.findById(carne); }
    public Estudiante guardar(Estudiante estudiante) { return estudianteRepository.save(estudiante); }

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

    @Transactional
    public void eliminar(Integer carne) {
       
        usuarioRepository.findAll().stream()
            .filter(u -> u.getEstudiante() != null && u.getEstudiante().getCarne().equals(carne))
            .forEach(u -> {
                u.setEstudiante(null);
                usuarioRepository.save(u);
            });

        
        List<com.Gestor.Proyecto.model.Justificacion> justificaciones =
            justificacionRepository.findByEstudianteCarne(carne);
        for (var j : justificaciones) {
            notificacionRepository.findAll().stream()
                .filter(n -> n.getJustificacion() != null && n.getJustificacion().getId().equals(j.getId()))
                .forEach(n -> notificacionRepository.deleteById(n.getId()));
            fechaAusenciaRepository.findByJustificacionId(j.getId())
                .forEach(f -> fechaAusenciaRepository.deleteById(f.getId()));
        }
        justificacionRepository.deleteAll(justificaciones);

        
        actividadAcademicaRepository.findByEstudianteCarne(carne)
            .forEach(a -> actividadAcademicaRepository.deleteById(a.getId()));

        
        telefonoEstudianteRepository.findByEstudianteCarne(carne)
            .forEach(t -> telefonoEstudianteRepository.deleteById(t.getId()));
        contactoRepository.findByEstudianteCarne(carne).forEach(c -> {
            telefonoContactoRepository.findByContactoDpi(c.getDpi())
                .forEach(t -> telefonoContactoRepository.deleteById(t.getId()));
            contactoRepository.deleteById(c.getDpi());
        });

        
        estudianteRepository.deleteById(carne);
    }
}