-- Script SQL para criar o banco de dados do sistema de marmitas

CREATE DATABASE marmitadb;
USE marmitadb;

-- Criar tabela de Categorias
CREATE TABLE Categorias (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(200),
    ativo BIT DEFAULT 1
);

-- Criar tabela de Cozinheiros
CREATE TABLE Cozinheiros (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(500),
    telefone VARCHAR(15),
    ativo BIT DEFAULT 1
);

-- Criar tabela de Usuários
CREATE TABLE Usuarios (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    endereco VARCHAR(500),
    role VARCHAR(20) DEFAULT 'cliente',
    data_criacao DATETIME DEFAULT GETDATE(),
    ativo BIT DEFAULT 1
);

-- Criar tabela de Marmitas
CREATE TABLE Marmitas (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    categoria_id BIGINT NOT NULL,
    cozinheiro_id BIGINT NOT NULL,
    tempo_preparo_minutos INT NOT NULL,
    data_criacao DATETIME DEFAULT GETDATE(),
    disponivel BIT DEFAULT 1,
    FOREIGN KEY (categoria_id) REFERENCES Categorias(id),
    FOREIGN KEY (cozinheiro_id) REFERENCES Cozinheiros(id)
);

-- Inserir dados de exemplo

-- Categorias
INSERT INTO Categorias (nome, descricao) VALUES 
('Tradicional', 'Pratos tradicionais brasileiros'),
('Fitness', 'Opções saudáveis e nutritivas'),
('Vegetariana', 'Pratos sem carne'),
('Vegana', 'Pratos 100% vegetais'),
('Low Carb', 'Baixo teor de carboidratos');

-- Cozinheiros
INSERT INTO Cozinheiros (nome, especialidade, telefone) VALUES 
('Maria Silva', 'Culinária tradicional brasileira', '(11) 99999-1111'),
('João Santos', 'Comida fitness e saudável', '(11) 99999-2222'),
('Ana Costa', 'Culinária vegetariana e vegana', '(11) 99999-3333'),
('Carlos Oliveira', 'Especialista em low carb', '(11) 99999-4444');

-- Usuários
INSERT INTO Usuarios (nome, email, senha, telefone, endereco, role) VALUES 
('Pedro Cliente', 'pedro@email.com', '123456', '(11) 88888-1111', 'Rua das Flores, 123', 'cliente'),
('Julia Administradora', 'julia@marmita.com', 'admin123', '(11) 88888-2222', 'Av. Principal, 456', 'admin'),
('Roberto Silva', 'roberto@email.com', '123456', '(11) 88888-3333', 'Rua do Comércio, 789', 'cliente');

-- Marmitas
INSERT INTO Marmitas (nome, descricao, preco, categoria_id, cozinheiro_id, tempo_preparo_minutos) VALUES 
('Frango Grelhado Tradicional', 'Frango grelhado, arroz branco, feijão carioca e salada verde', 15.90, 1, 1, 30),
('Peixe com Legumes Fitness', 'Peixe grelhado com legumes no vapor, quinoa e molho de ervas', 18.50, 2, 2, 25),
('Lasanha de Berinjela Vegetariana', 'Lasanha de berinjela com queijo e molho de tomate caseiro', 16.80, 3, 3, 45),
('Salada Completa Vegana', 'Mix de folhas, grão de bico, quinoa, tomate cereja e molho tahine', 14.90, 4, 3, 15),
('Salmão Low Carb', 'Salmão grelhado com brócolis, couve-flor e abobrinha refogada', 22.90, 5, 4, 20),
('Feijoada Completa', 'Feijoada tradicional com arroz, couve, farofa e laranja', 19.90, 1, 1, 60),
('Frango Teriyaki Fitness', 'Frango teriyaki com batata doce e mix de vegetais', 17.50, 2, 2, 35),
('Hambúrguer Vegano', 'Hambúrguer de grão de bico com batata rústica e salada', 16.50, 4, 3, 25);

-- Consultas para verificar os dados
SELECT 'CATEGORIAS' as TABELA;
SELECT * FROM Categorias;

SELECT 'COZINHEIROS' as TABELA;
SELECT * FROM Cozinheiros;

SELECT 'USUÁRIOS' as TABELA;
SELECT * FROM Usuarios;




SELECT * FROM Marmitas;
UPDATE Marmitas 
SET preco = 16.90 
WHERE id = 1;

SELECT 'MARMITAS' as TABELA;
SELECT m.id, m.nome, m.preco, c.nome as categoria, coz.nome as cozinheiro, m.tempo_preparo_minutos
FROM Marmitas m
JOIN Categorias c ON m.categoria_id = c.id
JOIN Cozinheiros coz ON m.cozinheiro_id = coz.id
ORDER BY m.id;


IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('Marmitas') AND name = 'imagem_url')
BEGIN
    ALTER TABLE Marmitas ADD imagem_url VARCHAR(500);
    PRINT 'Campo imagem_url adicionado com sucesso!';
END
ELSE
BEGIN
    PRINT 'Campo imagem_url já existe na tabela.';
END


USE marmitadb;
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Usuarios';


USE marmitadb;
SELECT COUNT(*) as total_usuarios FROM Usuarios;
SELECT id, nome, email, role FROM Usuarios;