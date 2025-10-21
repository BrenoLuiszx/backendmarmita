package com.marmita.api.controller;

import com.marmita.api.dto.LoginDTO;
import com.marmita.api.dto.UsuarioDTO;
import com.marmita.api.model.entity.Usuario;
import com.marmita.api.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
        
        if (usuario.isPresent() && usuario.get().getSenha().equals(loginDTO.getSenha()) && usuario.get().getAtivo()) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.get().getId());
            usuarioDTO.setNome(usuario.get().getNome());
            usuarioDTO.setEmail(usuario.get().getEmail());
            usuarioDTO.setRole(usuario.get().getRole());
            usuarioDTO.setAtivo(usuario.get().getAtivo());
            
            return ResponseEntity.ok(usuarioDTO);
        }
        
        return ResponseEntity.status(401).body("Email ou senha inválidos");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        // Verifica se email já existe
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email já cadastrado");
        }
        
        Usuario novoUsuario = usuarioRepository.save(usuario);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(novoUsuario.getId());
        usuarioDTO.setNome(novoUsuario.getNome());
        usuarioDTO.setEmail(novoUsuario.getEmail());
        usuarioDTO.setRole(novoUsuario.getRole());
        usuarioDTO.setAtivo(novoUsuario.getAtivo());
        
        return ResponseEntity.ok(usuarioDTO);
    }
}