package com.escola.cursos;

public class CursoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private String categoria;
    private String instrutor;
    private Integer duracao;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.titulo = curso.getTitulo();
        this.descricao = curso.getDescricao();
        this.url = curso.getUrl();
        this.categoria = curso.getCategoria().getNome();
        this.instrutor = curso.getInstrutor().getNome();
        this.duracao = curso.getDuracao();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getUrl() { return url; }
    public String getCategoria() { return categoria; }
    public String getInstrutor() { return instrutor; }
    public Integer getDuracao() { return duracao; }
}