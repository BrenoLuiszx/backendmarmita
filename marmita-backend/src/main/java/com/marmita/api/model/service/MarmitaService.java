package com.marmita.api.model.service;

import com.marmita.api.dto.MarmitaDTO;
import com.marmita.api.model.entity.Marmita;
import com.marmita.api.model.repository.MarmitaRepository;
import com.marmita.api.model.repository.CategoriaRepository;
import com.marmita.api.model.repository.CozinheiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarmitaService {

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CozinheiroRepository cozinheiroRepository;

    public List<MarmitaDTO> listarTodas() {
        return marmitaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MarmitaDTO> listarDisponiveis() {
        return marmitaRepository.findByDisponivelTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MarmitaDTO> buscarPorId(Long id) {
        return marmitaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public MarmitaDTO salvar(Marmita marmita) {
        Marmita marmitaSalva = marmitaRepository.save(marmita);
        return convertToDTO(marmitaSalva);
    }

    public Optional<MarmitaDTO> atualizar(Long id, MarmitaDTO marmitaDTO) {
        return marmitaRepository.findById(id).map(marmita -> {
            // Busca categoria e cozinheiro
            var categoria = categoriaRepository.findById(marmitaDTO.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            var cozinheiro = cozinheiroRepository.findById(marmitaDTO.getCozinheiroId())
                .orElseThrow(() -> new RuntimeException("Cozinheiro não encontrado"));
            
            // Atualiza os campos
            marmita.setNome(marmitaDTO.getNome());
            marmita.setDescricao(marmitaDTO.getDescricao());
            marmita.setPreco(marmitaDTO.getPreco());
            marmita.setCategoria(categoria);
            marmita.setCozinheiro(cozinheiro);
            marmita.setTempoPreparoMinutos(marmitaDTO.getTempoPreparoMinutos());
            marmita.setImagemUrl(marmitaDTO.getImagemUrl());
            
            Marmita marmitaSalva = marmitaRepository.save(marmita);
            return convertToDTO(marmitaSalva);
        });
    }

    public void deletar(Long id) {
        marmitaRepository.deleteById(id);
    }

    private MarmitaDTO convertToDTO(Marmita marmita) {
        MarmitaDTO dto = new MarmitaDTO();
        dto.setId(marmita.getId());
        dto.setNome(marmita.getNome());
        dto.setDescricao(marmita.getDescricao());
        dto.setPreco(marmita.getPreco());
        dto.setCategoriaId(marmita.getCategoria().getId());
        dto.setCategoriaNome(marmita.getCategoria().getNome());
        dto.setCozinheiroId(marmita.getCozinheiro().getId());
        dto.setCozinheiroNome(marmita.getCozinheiro().getNome());
        dto.setTempoPreparoMinutos(marmita.getTempoPreparoMinutos());
        dto.setImagemUrl(marmita.getImagemUrl());
        dto.setDisponivel(marmita.getDisponivel());
        return dto;
    }
}