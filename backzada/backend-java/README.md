# Backend Java - API de Cursos

API REST desenvolvida com Spring Boot para gerenciar cursos, usuários e categorias.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- SQL Server
- Maven

## Configuração

1. Configure o SQL Server e crie o banco `LearnlyDB`
2. Ajuste as credenciais em `application.properties`
3. Execute: `mvn spring-boot:run`

## Endpoints

### Cursos
- `GET /api/cursos` - Listar todos os cursos
- `GET /api/cursos/{id}` - Buscar curso por ID
- `GET /api/cursos/buscar?titulo=` - Buscar por título
- `GET /api/cursos/categoria/{categoria}` - Buscar por categoria
- `POST /api/cursos` - Criar curso
- `PUT /api/cursos/{id}` - Atualizar curso
- `DELETE /api/cursos/{id}` - Deletar curso

### Usuários
- `POST /api/usuarios/registrar` - Registrar usuário
- `POST /api/usuarios/login` - Login

### Categorias
- `GET /api/categorias` - Listar categorias

## Porta

A API roda na porta 8080: http://localhost:8080