package com.tecsup.repository;

import com.tecsup.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByIdDocente(Long idDocente);
}