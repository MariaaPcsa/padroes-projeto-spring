# 🚀 Projeto Padrões Spring Boot

API REST desenvolvida com:

- Spring Boot
- Spring Data JPA
- OpenFeign
- H2 Database
- Swagger/OpenAPI
- JUnit 5
- Mockito

---

# 📌 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.5 |
| Spring Cloud | 2023.0.3 |
| Maven | 3.9+ |

---

# 📌 Executar Projeto

## Rodar aplicação

```bash
mvn spring-boot:run




📌 Executar Testes
mvn test

test
📌 Gerar Cobertura
mvn clean test

Relatório:

target/site/jacoco/index.html
📌 Swagger

Acesse:

http://localhost:8081/swagger-ui.html
🚀 AGORA TESTE
POST
{
  "nome": "Maria",
  "endereco": {
    "cep": "01001000"
  }
}
✅ Resultado esperado

Status:

201 Created
🚀 UPDATE
{
  "nome": "Maria Atualizada",
  "endereco": {
    "cep": "01310930"
  }
}
✅ Resultado esperado
200 OK
🚀 DELETE
204 No Content

🚀LISTAR CLIENTES
GET
ID: 1
✅ Resultado esperado
Status:
200 OK
Body:
{
  "id": 2,
  "nome": "Maria Atualizada",
  "endereco": {
    "cep": "01310930",
    "logradouro": "Avenida Paulista",
    "complemento": "2100",
    "bairro": "Bela Vista",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "gia": "1004",
    "ddd": "11",
    "siafi": "7107"
  }
}
    
    }
]   

      
                 

📌 H2 Console

Acesse:

http://localhost:8080/h2-console
Configuração H2
Campo	Valor
JDBC URL	jdbc:h2:mem:testdb
User	sa
Password	vazio

Ver estrutura da tabela
CLIENTE
Buscar todos clientes
SELECT * FROM CLIENTES 

ENDERECO
Buscar todos endereços
SELECT * FROM ENDERECOS 

Buscar cliente por ID

SELECT * 
FROM CLIENTES
WHERE ID = 1;

Buscar por nome
SELECT *
FROM CLIENTES
WHERE NOME = 'Maria';

Buscar nomes parecidos
SELECT *
FROM CLIENTES
WHERE NOME LIKE '%Maria%';

Ver cliente + endereço
SELECT
    c.ID,
    c.NOME,
    e.CEP,
    e.LOGRADOURO,
    e.BAIRRO,
    e.LOCALIDADE,
    e.UF
FROM CLIENTES c
INNER JOIN ENDERECOS e
ON c.ENDERECO_CEP = e.CEP;




📌 Estrutura do Projeto
src/main/java/com/maria/padroes_projeto_spring
│
├── client
├── controller
├── dto
├── exception
├── model
├── repository
├── service
🚀 Estrutura de testes
src/test/java/com/maria/padroes_projeto_spring
│
├── controller
│   └── ClienteRestControllerTest.java
│
├── service
│   └── ClienteServiceImplTest.java
│
└── integration
    └── ClienteIntegrationTest.java

├── target/
├── .gitignore
├── HELP.md
├── pom.xml
│
│

📌 Funcionalidades

✅ CRUD de clientes
✅ Integração com ViaCEP
✅ Validação de dados
✅ Swagger/OpenAPI
✅ Testes unitários
✅ Testes de integração
✅ Cobertura com JaCoCo

📌 Melhorias Futuras
Spring Security
JWT
Docker
PostgreSQL
CI/CD
Resilience4j
Logs estruturados



---

# 🚀 Estrutura ideal final

```txt id="kzbw8w"
padroes-projeto-spring/
│
├── .gitignore
├── HELP.md
├── pom.xml
│
├── src/
│
├── target/



📌 Autor

Maria Correia


http://localhost:8080/swagger-ui.html

🚀 AGORA EXECUTE
Limpar projeto
mvn clean
Baixar dependências novamente
mvn clean install
Rodar testes
mvn test


Faça:

mvn dependency:purge-local-repository

Depois:

mvn clean install


🚀 URLs do projeto
Swagger
http://localhost:8080/swagger-ui.html
H2
http://localhost:8080/h2-console



✅ Commit recomendado
git add .
git commit -m "build: corrige versões incompatíveis do Spring Boot e Spring Cloud"
git commit -m "build: adiciona dependências de testes Spring Boot"