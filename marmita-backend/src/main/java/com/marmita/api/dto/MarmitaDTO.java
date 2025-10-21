package com.marmita.api.dto;

import java.math.BigDecimal;

public class MarmitaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Long categoriaId;
    private String categoriaNome;
    private Long cozinheiroId;
    private String cozinheiroNome;
    private Integer tempoPreparoMinutos;
    private String imagemUrl;
    private Boolean disponivel;

    public MarmitaDTO() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }

    public Long getCozinheiroId() { return cozinheiroId; }
    public void setCozinheiroId(Long cozinheiroId) { this.cozinheiroId = cozinheiroId; }

    public String getCozinheiroNome() { return cozinheiroNome; }
    public void setCozinheiroNome(String cozinheiroNome) { this.cozinheiroNome = cozinheiroNome; }

    public Integer getTempoPreparoMinutos() { return tempoPreparoMinutos; }
    public void setTempoPreparoMinutos(Integer tempoPreparoMinutos) { this.tempoPreparoMinutos = tempoPreparoMinutos; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
}