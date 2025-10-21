package com.marmita.api.controller;

import com.marmita.api.model.entity.Cozinheiro;
import com.marmita.api.model.repository.CozinheiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cozinheiros")
@CrossOrigin(origins = "*")
public class CozinheiroController {

    @Autowired
    private CozinheiroRepository cozinheiroRepository;

    @GetMapping
    public List<Cozinheiro> listarTodos() {
        return cozinheiroRepository.findByAtivoTrue();
    }

    @PostMapping
    public ResponseEntity<Cozinheiro> criar(@RequestBody Cozinheiro cozinheiro) {
        Cozinheiro cozinheiroSalvo = cozinheiroRepository.save(cozinheiro);
        return ResponseEntity.ok(cozinheiroSalvo);
    }
}