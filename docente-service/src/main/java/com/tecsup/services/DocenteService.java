package com.tecsup.services;

import com.tecsup.dto.DocenteDTO;
import com.tecsup.exception.DuplicateResourceException;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Docente;
import com.tecsup.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {

    private final DocenteRepository repository;

    public DocenteService(DocenteRepository repository) {
        this.repository = repository;
    }

    public List<Docente> listar() {
        return repository.findAll();
    }

    public Docente obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + id));
    }

    public Docente registrar(DocenteDTO dto) {
        if (repository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("Ya existe un docente con DNI: " + dto.getDni());
        }
        Docente docente = new Docente();
        copiarDatos(dto, docente);
        return repository.save(docente);
    }

    public Docente actualizar(Long id, DocenteDTO dto) {
        Docente docente = obtener(id);
        if (repository.existsByDniAndIdNot(dto.getDni(), id)) {
            throw new DuplicateResourceException("Ya existe otro docente con DNI: " + dto.getDni());
        }
        copiarDatos(dto, docente);
        return repository.save(docente);
    }

    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void copiarDatos(DocenteDTO dto, Docente docente) {
        docente.setNombres(dto.getNombres());
        docente.setApellidos(dto.getApellidos());
        docente.setDni(dto.getDni());
        docente.setCorreo(dto.getCorreo());
        docente.setEspecialidad(dto.getEspecialidad());
        docente.setEstado(dto.getEstado());
    }
}