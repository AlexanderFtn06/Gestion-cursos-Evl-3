package com.tecsup.services;

import com.tecsup.dto.EstadoDTO;
import com.tecsup.dto.InscripcionDTO;
import com.tecsup.exception.DuplicateResourceException;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Inscripcion;
import com.tecsup.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository repository;

    public InscripcionService(InscripcionRepository repository) {
        this.repository = repository;
    }

    public List<Inscripcion> listar() {
        return repository.findAll();
    }

    public List<Inscripcion> listarPorCurso(Long idCurso) {
        return repository.findByIdCurso(idCurso);
    }

    public Inscripcion obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + id));
    }

    public Inscripcion registrar(InscripcionDTO dto) {
        if (repository.existsByIdCursoAndDni(dto.getIdCurso(), dto.getDni())) {
            throw new DuplicateResourceException(
                    "El participante con DNI " + dto.getDni() + " ya está inscrito en el curso " + dto.getIdCurso());
        }
        Inscripcion inscripcion = new Inscripcion();
        copiarDatos(dto, inscripcion);
        return repository.save(inscripcion);
    }

    public Inscripcion actualizar(Long id, InscripcionDTO dto) {
        Inscripcion inscripcion = obtener(id);
        if (repository.existsByIdCursoAndDniAndIdNot(dto.getIdCurso(), dto.getDni(), id)) {
            throw new DuplicateResourceException(
                    "El participante con DNI " + dto.getDni() + " ya está inscrito en el curso " + dto.getIdCurso());
        }
        copiarDatos(dto, inscripcion);
        return repository.save(inscripcion);
    }

    public Inscripcion cambiarEstado(Long id, EstadoDTO dto) {
        Inscripcion inscripcion = obtener(id);
        inscripcion.setEstado(dto.getEstado());
        return repository.save(inscripcion);
    }

    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void copiarDatos(InscripcionDTO dto, Inscripcion inscripcion) {
        inscripcion.setIdCurso(dto.getIdCurso());
        inscripcion.setNombres(dto.getNombres());
        inscripcion.setApellidos(dto.getApellidos());
        inscripcion.setDni(dto.getDni());
        inscripcion.setCorreo(dto.getCorreo());
        inscripcion.setFecha(dto.getFecha());
        inscripcion.setEstado(dto.getEstado());
    }
}