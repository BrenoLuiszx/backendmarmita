package com.marmita.api.controller;

import com.marmita.api.dto.MarmitaDTO;
import com.marmita.api.model.entity.Marmita;
import com.marmita.api.model.service.MarmitaService;
import com.marmita.api.model.repository.CategoriaRepository;
import com.marmita.api.model.repository.CozinheiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marmitas")
@CrossOrigin(origins = "*")
public class MarmitaController {

    @Autowired
    private MarmitaService marmitaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CozinheiroRepository cozinheiroRepository;

    @GetMapping
    public List<MarmitaDTO> listarTodas() {
        return marmitaService.listarTodas();
    }

    @GetMapping("/disponiveis")
    public List<MarmitaDTO> listarDisponiveis() {
        return marmitaService.listarDisponiveis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarmitaDTO> buscarPorId(@PathVariable Long id) {
        Optional<MarmitaDTO> marmita = marmitaService.buscarPorId(id);
        return marmita.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarmitaDTO> criar(@RequestBody MarmitaDTO marmitaDTO) {
        try {
            // Busca categoria e cozinheiro
            var categoria = categoriaRepository.findById(marmitaDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            var cozinheiro = cozinheiroRepository.findById(marmitaDTO.getCozinheiroId())
                .orElseThrow(() -> new RuntimeException("Cozinheiro não encontrado"));
            
            // Cria nova marmita
            Marmita marmita = new Marmita();
            marmita.setNome(marmitaDTO.getNome());
            marmita.setDescricao(marmitaDTO.getDescricao());
            marmita.setPreco(marmitaDTO.getPreco());
            marmita.setCategoria(categoria);
            marmita.setCozinheiro(cozinheiro);
            marmita.setTempoPreparoMinutos(marmitaDTO.getTempoPreparoMinutos());
            marmita.setImagemUrl(marmitaDTO.getImagemUrl());
            
            MarmitaDTO marmitaSalva = marmitaService.salvar(marmita);
            return ResponseEntity.ok(marmitaSalva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarmitaDTO> atualizar(@PathVariable Long id, @RequestBody MarmitaDTO marmitaDTO) {
        try {
            return marmitaService.atualizar(id, marmitaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!marmitaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        marmitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}