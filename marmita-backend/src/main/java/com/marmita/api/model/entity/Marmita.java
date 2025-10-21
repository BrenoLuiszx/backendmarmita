package com.marmita.api.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Marmitas")
public class Marmita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(nullable = false, length = 1000)
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "cozinheiro_id", nullable = false)
    private Cozinheiro cozinheiro;
    
    @Column(nullable = false)
    private Integer tempoPreparoMinutos;
    
    @Column(name = "imagem_url", length = 500)
    private String imagemUrl;
    
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    private Boolean disponivel = true;

    public Marmita() {}

    public Marmita(String nome, String descricao, BigDecimal preco, Categoria categoria, 
                   Cozinheiro cozinheiro, Integer tempoPreparoMinutos) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.cozinheiro = cozinheiro;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Cozinheiro getCozinheiro() { return cozinheiro; }
    public void setCozinheiro(Cozinheiro cozinheiro) { this.cozinheiro = cozinheiro; }

    public Integer getTempoPreparoMinutos() { return tempoPreparoMinutos; }
    public void setTempoPreparoMinutos(Integer tempoPreparoMinutos) { this.tempoPreparoMinutos = tempoPreparoMinutos; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
}