package com.escola.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "http://localhost:5173")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(c -> ResponseEntity.ok(new CursoDTO(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<CursoDTO> buscarPorTitulo(@RequestParam String titulo) {
        return cursoRepository.findByTituloContaining(titulo).stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/categoria/{categoria}")
    public List<CursoDTO> buscarPorCategoria(@PathVariable String categoria) {
        return cursoRepository.findByCategoriaNome(categoria).stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            curso.setTitulo(cursoAtualizado.getTitulo());
            curso.setDescricao(cursoAtualizado.getDescricao());
            curso.setUrl(cursoAtualizado.getUrl());
            curso.setCategoria(cursoAtualizado.getCategoria());
            curso.setInstrutor(cursoAtualizado.getInstrutor());
            curso.setDuracao(cursoAtualizado.getDuracao());
            return ResponseEntity.ok(cursoRepository.save(curso));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}