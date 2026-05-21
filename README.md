# 🚀 padroes-projeto-spring

API REST desenvolvida com Spring Boot para gerenciamento de clientes, utilizando integração com ViaCEP,
banco H2 Database, documentação Swagger/OpenAPI e boas práticas de arquitetura em camadas.

# 📚 Tecnologias utilizadas
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
Spring Validation
OpenFeign
H2 Database
Swagger/OpenAPI
Maven
JUnit 5
Mockito

# 📌 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.5 |
| Spring Cloud | 2023.0.3 |
| Maven | 3.9+ |

---

# 🏗️ Arquitetura do projeto

ontroller → Service → Repository → Database
Além disso, utiliza alguns padrões de projeto:

Strategy
Facade
Singleton
DTO
Repository Pattern

# 📁 Estrutura do projeto
src/main/java/com/maria/padroes_projeto_spring
│
├── client
├── controller
├── dto
├── exception
├── model
├── repository
├── service
# 🚀 Estrutura de testes
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



# 🚀 Funcionalidades

✅ Cadastro de clientes
✅ Atualização de clientes
✅ Remoção de clientes
✅ Busca por ID
✅ Busca de todos os clientes
✅ Busca por nome
✅ Integração automática com ViaCEP
✅ Persistência de endereços
✅ Documentação Swagger
✅ Banco H2
✅ Validações com Bean Validation
✅ Tratamento de erros
✅ Testes unitários



# 🔗 Integração ViaCEP

Ao cadastrar um cliente com CEP válido, a API consulta automaticamente o ViaCEP para preencher os dados do endereço.

Exemplo
{
"nome": "Maria",
"endereco": {
"cep": "01001000"
}
}




# 📌 Executar Projeto

## Rodar aplicação


mvn spring-boot:run

# 📌 Executar Testes
mvn test

test
# 📌 Gerar Cobertura
mvn clean test

Relatório:
target/site/jacoco/index.html


📌 Swagger

Acesse:

http://localhost:8081/swagger-ui.html

ou

http://localhost:8081/swagger-ui/index.html

📌 Endpoints da API
Método	Endpoint	Descrição
GET	/clientes	Lista todos os clientes
GET	/clientes/{id}	Busca cliente por ID
POST	/clientes	Cadastra cliente
PUT	/clientes/{id}	Atualiza cliente
DELETE	/clientes/{id}	Remove cliente
GET	/clientes/buscar?nome=	Busca cliente por nome

# 🚀 AGORA TESTE
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

      
                 

# 📌 H2 Console

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




# Melhorias Futuras
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




✅ Commit recomendado
git add .
git commit -m "build: corrige versões incompatíveis do Spring Boot e Spring Cloud"
git commit -m "build: adiciona dependências de testes Spring Boot"
