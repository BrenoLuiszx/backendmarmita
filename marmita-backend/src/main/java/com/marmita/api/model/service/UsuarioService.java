package com.marmita.api.model.service;

import com.marmita.api.dto.UsuarioDTO;
import com.marmita.api.model.entity.Usuario;
import com.marmita.api.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> listarAtivos() {
        return usuarioRepository.findByAtivoTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToDTO);
    }

    public UsuarioDTO salvar(Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return convertToDTO(usuarioSalvo);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setEndereco(usuario.getEndereco());
        dto.setRole(usuario.getRole());
        dto.setAtivo(usuario.getAtivo());
        return dto;
    }
}