package com.escola.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        
        if (usuarioExistente.isPresent()) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("error", "Email já cadastrado");
            return ResponseEntity.badRequest().body(erro);
        }

        Usuario novoUsuario = usuarioRepository.save(usuario);
        
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("message", "Usuário cadastrado com sucesso");
        
        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("id", novoUsuario.getId());
        usuarioData.put("nome", novoUsuario.getNome());
        usuarioData.put("email", novoUsuario.getEmail());
        usuarioData.put("foto", novoUsuario.getFoto());
        
        resposta.put("usuario", usuarioData);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String senha = loginData.get("senha");
        
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("message", "Login realizado com sucesso");
            
            Map<String, Object> usuarioData = new HashMap<>();
            usuarioData.put("id", u.getId());
            usuarioData.put("nome", u.getNome());
            usuarioData.put("email", u.getEmail());
            usuarioData.put("foto", u.getFoto());
            
            resposta.put("usuario", usuarioData);
            return ResponseEntity.ok(resposta);
        } else {
            Map<String, Object> erro = new HashMap<>();
            erro.put("error", "Email ou senha inválidos");
            return ResponseEntity.badRequest().body(erro);
        }
    }
}