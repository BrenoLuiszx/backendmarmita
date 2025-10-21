package com.escola.cursos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c WHERE c.titulo LIKE %:titulo%")
    List<Curso> findByTituloContaining(@Param("titulo") String titulo);
    
    @Query("SELECT c FROM Curso c WHERE c.categoria.nome = :categoria")
    List<Curso> findByCategoriaNome(@Param("categoria") String categoria);
}