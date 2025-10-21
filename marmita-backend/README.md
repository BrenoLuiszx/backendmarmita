# Marmita API - Sistema de Delivery de Marmitas

Backend desenvolvido em Spring Boot para sistema de delivery de marmitas, baseado no template backend-learnly.

## Funcionalidades

- **Gestão de Marmitas**: CRUD completo para marmitas
- **Gestão de Usuários**: Cadastro e gerenciamento de clientes
- **Categorias**: Organização das marmitas por categorias
- **Cozinheiros**: Cadastro dos responsáveis pelo preparo

## Tecnologias

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- H2 Database (em memória)
- Maven

## Como executar

1. Navegue até a pasta do projeto:
```bash
cd marmita-backend
```

2. Execute o projeto:
```bash
cd marmita-backend
```

3. A aplicação estará disponível em: `http://localhost:8080`

## Endpoints principais

### Marmitas
- `GET /api/marmitas` - Listar todas as marmitas
- `GET /api/marmitas/disponiveis` - Listar marmitas disponíveis
- `GET /api/marmitas/{id}` - Buscar marmita por ID
- `POST /api/marmitas` - Criar nova marmita
- `PUT /api/marmitas/{id}` - Atualizar marmita
- `DELETE /api/marmitas/{id}` - Deletar marmita

### Usuários
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/ativos` - Listar usuários ativos
- `GET /api/usuarios/{id}` - Buscar usuário por ID
- `POST /api/usuarios` - Criar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Deletar usuário

## Console H2

Acesse o console do banco H2 em: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:marmitadb`
- **Username**: `sa`
- **Password**: (deixar em branco)

## Estrutura do Projeto

```
src/main/java/com/marmita/api/
├── config/          # Configurações (CORS, DataInitializer)
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── model/
│   ├── entity/     # Entidades JPA
│   ├── repository/ # Repositórios
│   └── service/    # Serviços de negócio
└── MarmitaApiApplication.java
```

## Dados de Exemplo

O sistema é inicializado com dados de exemplo:
- 3 categorias (Tradicional, Fitness, Vegetariana)
- 2 cozinheiros
- 2 marmitas
- 1 usuário cliente