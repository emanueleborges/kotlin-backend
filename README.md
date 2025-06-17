# Kotlin Backend - Spring Boot + JPA

Este projeto é um exemplo de CRUD completo utilizando Kotlin com Spring Boot e banco de dados H2 em memória.

---

## ✅ Tecnologias Utilizadas

- Kotlin 1.9
- Spring Boot 3.1
- Spring Data JPA
- H2 Database
- Gradle Kotlin DSL

---

## ▶️ Como Executar

### Pré-requisitos
- JDK 17 ou superior
- IntelliJ IDEA (recomendado)

### Passos para rodar:

```bash
# Navegue até o diretório do projeto
cd backend

# Execute a aplicação
./gradlew bootRun
```

Ou, no IntelliJ:
- Abra o projeto via **File > Open...**
- Clique com o botão direito em `CrudApplication.kt` e selecione **Run**

---

## 🧪 Endpoints REST

- `GET /users` → Lista todos os usuários
- `POST /users` → Cria novo usuário
  - Exemplo JSON: `{ "name": "João" }`
- `PUT /users/{id}` → Atualiza o usuário com o ID informado
- `DELETE /users/{id}` → Remove o usuário pelo ID

---

## ⚙️ Configuração do Banco de Dados (H2)

A aplicação usa um banco em memória H2, configurado no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

> Acesse o console do H2 em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 📁 Estrutura do Projeto
```
backend/
├── build.gradle.kts
├── src/
│   └── main/
│       ├── kotlin/
│       │   └── com/example/
│       │       ├── CrudApplication.kt
│       │       ├── User.kt
│       │       ├── UserController.kt
│       │       └── UserRepository.kt
│       └── resources/
│           └── application.properties
└── README.md
```

---

## 📄 Licença
Este projeto está licenciado sob a licença MIT.
