package com.marmita.api.config;

import com.marmita.api.model.entity.*;
import com.marmita.api.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CozinheiroRepository cozinheiroRepository;

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Só criar dados se não existirem
        if (categoriaRepository.count() == 0) {
            // Criar categorias baseadas no db.json
            Categoria tradicional = new Categoria("Tradicional", "Pratos tradicionais brasileiros");
            Categoria fitness = new Categoria("Fitness", "Opções saudáveis e nutritivas");
            Categoria vegetariana = new Categoria("Vegetariana", "Pratos sem carne");
            Categoria vegana = new Categoria("Vegana", "Pratos 100% vegetais");
            Categoria lowCarb = new Categoria("Low Carb", "Baixo teor de carboidratos");
            
            categoriaRepository.save(tradicional);
            categoriaRepository.save(fitness);
            categoriaRepository.save(vegetariana);
            categoriaRepository.save(vegana);
            categoriaRepository.save(lowCarb);

            // Criar cozinheiros
            Cozinheiro maria = new Cozinheiro("Maria Silva", "Culinária tradicional brasileira", "(11) 99999-1111");
            Cozinheiro joao = new Cozinheiro("João Santos", "Comida fitness e saudável", "(11) 99999-2222");
            Cozinheiro ana = new Cozinheiro("Ana Costa", "Culinária vegetariana e vegana", "(11) 99999-3333");
            Cozinheiro carlos = new Cozinheiro("Carlos Oliveira", "Especialista em low carb", "(11) 99999-4444");
            
            cozinheiroRepository.save(maria);
            cozinheiroRepository.save(joao);
            cozinheiroRepository.save(ana);
            cozinheiroRepository.save(carlos);

            // Criar marmitas baseadas no db.json
            Marmita marmita1 = new Marmita("Carne Moída e Legumes", 
                                          "Carne moída refogada com legumes frescos, arroz branco e feijão", 
                                          new BigDecimal("20.00"), tradicional, maria, 35);
            
            Marmita marmita2 = new Marmita("Carne de Soja com Abóbora", 
                                          "Carne de soja nutritiva com abóbora refogada, opção vegetariana", 
                                          new BigDecimal("20.00"), vegetariana, ana, 25);
            
            Marmita marmita3 = new Marmita("Frango Grelhado Tradicional", 
                                          "Frango grelhado, arroz branco, feijão carioca e salada verde", 
                                          new BigDecimal("18.50"), tradicional, maria, 30);
            
            Marmita marmita4 = new Marmita("Peixe com Legumes Fitness", 
                                          "Peixe grelhado com legumes no vapor, quinoa e molho de ervas", 
                                          new BigDecimal("22.90"), fitness, joao, 25);
            
            Marmita marmita5 = new Marmita("Salada Completa Vegana", 
                                          "Mix de folhas, grão de bico, quinoa, tomate cereja e molho tahine", 
                                          new BigDecimal("16.90"), vegana, ana, 15);
            
            Marmita marmita6 = new Marmita("Salmão Low Carb", 
                                          "Salmão grelhado com brócolis, couve-flor e abobrinha refogada", 
                                          new BigDecimal("25.90"), lowCarb, carlos, 20);
            
            marmitaRepository.save(marmita1);
            marmitaRepository.save(marmita2);
            marmitaRepository.save(marmita3);
            marmitaRepository.save(marmita4);
            marmitaRepository.save(marmita5);
            marmitaRepository.save(marmita6);

            // Criar usuários baseados no db.json
            Usuario admin = new Usuario("Esther", "admin@admin.com.br", "123456", 
                                      "(11) 99999-0000", "Endereço Admin");
            admin.setRole("admin");
            usuarioRepository.save(admin);
            
            Usuario cliente1 = new Usuario("Ana Costa", "ana@email.com", "123456", 
                                         "(11) 88888-1111", "Rua das Flores, 123");
            usuarioRepository.save(cliente1);
            
            Usuario cliente2 = new Usuario("Pedro Silva", "pedro@email.com", "123456", 
                                         "(11) 88888-2222", "Av. Principal, 456");
            usuarioRepository.save(cliente2);
            
            Usuario cliente3 = new Usuario("Roberto Santos", "roberto@email.com", "123456", 
                                         "(11) 88888-3333", "Rua do Comércio, 789");
            usuarioRepository.save(cliente3);

            System.out.println("Dados do db.json migrados com sucesso para o banco SQL Server!");
        }
    }
}