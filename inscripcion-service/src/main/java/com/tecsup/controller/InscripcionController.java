package com.tecsup.controller;

import com.tecsup.dto.EstadoDTO;
import com.tecsup.dto.InscripcionDTO;
import com.tecsup.model.Inscripcion;
import com.tecsup.services.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService service;

    public InscripcionController(InscripcionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<Inscripcion>> listarPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.listarPorCurso(idCurso));
    }

    @PostMapping
    public ResponseEntity<Inscripcion> registrar(@Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizar(@PathVariable Long id, @Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Inscripcion> cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoDTO dto) {
        return ResponseEntity.ok(service.cambiarEstado(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Inscripción eliminada correctamente"));
    }
}