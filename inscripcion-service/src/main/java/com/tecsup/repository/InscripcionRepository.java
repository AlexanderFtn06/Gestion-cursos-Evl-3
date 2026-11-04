package com.tecsup.repository;

import com.tecsup.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByIdCurso(Long idCurso);

    boolean existsByIdCursoAndDni(Long idCurso, String dni);

    boolean existsByIdCursoAndDniAndIdNot(Long idCurso, String dni, Long id);
}