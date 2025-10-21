package com.escola.cursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private InstrutorRepository instrutorRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            carregarDadosIniciais();
        }
    }
    
    private void carregarDadosIniciais() {
        // Criar categorias
        Categoria programacao = new Categoria("Programação", "Cursos de desenvolvimento de software");
        Categoria design = new Categoria("Design", "Cursos de design gráfico e UX/UI");
        Categoria marketing = new Categoria("Marketing", "Cursos de marketing digital");
        
        categoriaRepository.save(programacao);
        categoriaRepository.save(design);
        categoriaRepository.save(marketing);
        
        // Criar instrutores
        Instrutor instrutor1 = new Instrutor("João Silva", "Desenvolvedor Full Stack com 10 anos de experiência", "https://via.placeholder.com/150");
        Instrutor instrutor2 = new Instrutor("Maria Santos", "Designer UX/UI especialista em interfaces modernas", "https://via.placeholder.com/150");
        
        instrutorRepository.save(instrutor1);
        instrutorRepository.save(instrutor2);
        
        // Criar cursos
        Curso curso1 = new Curso("Java Spring Boot", "Aprenda a desenvolver APIs REST com Spring Boot", "https://example.com/java-spring", programacao, instrutor1, 40);
        Curso curso2 = new Curso("React Fundamentals", "Fundamentos do React para desenvolvimento frontend", "https://example.com/react", programacao, instrutor1, 30);
        Curso curso3 = new Curso("UI/UX Design", "Design de interfaces modernas e intuitivas", "https://example.com/design", design, instrutor2, 25);
        
        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);
        
        // Criar usuário admin
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario("Admin", "admin@escola.com", "admin123", "https://via.placeholder.com/150");
            admin.setRole("admin");
            usuarioRepository.save(admin);
        }
    }
}