package com.tecsup.services;

import com.tecsup.dto.CursoDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Curso;
import com.tecsup.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listar() {
        return repository.findAll();
    }

    public List<Curso> listarPorDocente(Long idDocente) {
        return repository.findByIdDocente(idDocente);
    }

    public Curso obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
    }

    public Curso registrar(CursoDTO dto) {
        Curso curso = new Curso();
        copiarDatos(dto, curso);
        return repository.save(curso);
    }

    public Curso actualizar(Long id, CursoDTO dto) {
        Curso curso = obtener(id);
        copiarDatos(dto, curso);
        return repository.save(curso);
    }

    public void eliminar(Long id) {
        repository.delete(obtener(id));
    }

    private void copiarDatos(CursoDTO dto, Curso curso) {
        curso.setNombre(dto.getNombre());
        curso.setDuracion(dto.getDuracion());
        curso.setPrecio(dto.getPrecio());
        curso.setIdDocente(dto.getIdDocente());
        curso.setEstado(dto.getEstado());
    }
}