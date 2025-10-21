package com.escola.cursos;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String senha;
    
    @Column(length = 500)
    private String foto;
    
    @Column(length = 20)
    private String role = "user";

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.role = "user";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}