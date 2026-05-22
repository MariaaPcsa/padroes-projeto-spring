#  🚀 padroes-projeto-spring

API REST desenvolvida com Spring Boot para gerenciamento de clientes, utilizando integração com ViaCEP, banco H2 Database, documentação Swagger/OpenAPI e boas práticas de arquitetura em camadas.

#  📚 Tecnologias utilizadas
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

#  🏗️ Arquitetura do projeto

O projeto foi estruturado seguindo boas práticas de desenvolvimento backend:

Controller → Service → Repository → Database

Além disso, utiliza alguns padrões de projeto:

Strategy
Facade
Singleton
DTO
Repository Pattern

# 📁 Estrutura do projeto
src/main/java/com/maria/padroes_projeto_spring
│
├── PadroesProjetoSpringApplication.java
│
├── client
│   └── ViaCepClient.java
│
├── controller
│   └── ClienteRestController.java
│
├── dto
│   └── ViaCepResponseDTO.java
│
├── exception
│   └── GlobalExceptionHandler.java
│
├── model
│   ├── Cliente.java
│   └── Endereco.java
│
├── repository
│   ├── ClienteRepository.java
│   └── EnderecoRepository.java
│
├── service
│   ├── ClienteService.java
│   ├── ViaCepService.java
│   │
│   └── impl
│       ├── ClienteServiceImpl.java
│       └── ViaCepServiceImpl.java
#  🚀 Funcionalidades

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
# 🚀 Como executar o projeto

Clone o repositório
git clone https://github.com/MariaaPcsa/padroes-projeto-spring.git
Acesse a pasta
cd padroes-projeto-spring
Execute o projeto

#  🚀 AGORA EXECUTE
mvn clean install
#  Windows
mvn spring-boot:run

#  Linux/Mac
./mvnw spring-boot:run



#  🌐 URLs importantes

Swagger UI
http://localhost:8081/swagger-ui.html
ou
http://localhost:8081/swagger-ui/index.html

OpenAPI JSON
http://localhost:8081/v3/api-docs

#  H2 Console
http://localhost:8081/h2-console

# 🔐 Configuração H2
JDBC URL
jdbc:h2:mem:testdb
User
sa
Password
(deixe vazio)

#  🚀 CONSULTAS SQL

#  ✅ Buscar todos clientes
SELECT * FROM CLIENTES;
# ✅ Buscar todos endereços
SELECT * FROM ENDERECOS;
# ✅ Buscar cliente por ID
SELECT * 
FROM CLIENTES
WHERE ID = 1;
# ✅ Buscar por nome
SELECT *
FROM CLIENTES
WHERE NOME = 'Maria';
# ✅ Buscar nomes parecidos
SELECT *
FROM CLIENTES
WHERE NOME LIKE '%Maria%';

# ✅ Ver cliente + endereço
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

# 📌 Endpoints da API

Método	Endpoint	Descrição  
GET	/clientes	Lista todos os clientes  
GET	/clientes/{id}	Busca cliente por ID  
POST	/clientes	Cadastra cliente  
PUT	/clientes/{id}	Atualiza cliente  
DELETE	/clientes/{id}	Remove cliente  
GET	/clientes/buscar?nome=	Busca cliente por nome  

#  📦 Exemplo de cadastro
Request
{
  "nome": "Maria",
  "endereco": {
    "cep": "01001000"
  }
}
Response
{
  "id": 1,
  "nome": "Maria",
  "endereco": {
    "cep": "01001000",
    "logradouro": "Praça da Sé",
    "bairro": "Sé",
    "localidade": "São Paulo",
    "uf": "SP"
  }
}

# 🧪 Executando testes
mvn test
mvn verify


# 📊 Relatório Jacoco

Após executar os testes:

target/site/jacoco/index.html

#  🚀 Cenários de Teste

ID	Cenário	Método
CT-001	Listar clientes	GET
CT-002	Buscar cliente por ID	GET
CT-003	Cadastrar cliente válido	POST
CT-004	Atualizar cliente	PUT
CT-005	Deletar cliente	DELETE
CT-006	Validar nome obrigatório	POST
CT-007	Validar CEP inválido	POST
CT-008	Buscar cliente inexistente	GET

#  🛠️ Melhorias implementadas

Refatoração da arquitetura
Correção de serialização JSON
Tratamento de exceções
DTO para integração externa
Organização em camadas
Melhorias no Swagger
Validações de entrada
Padronização REST
Ajustes de status HTTP
Configuração profissional Maven

# DICAS DE COMMIT

🚀 TIPOS MAIS USADOS
Tipo	Uso
feat	nova funcionalidade
fix	correção
refactor	refatoração
test	testes
docs	documentação
style	formatação
chore configuração

# 🚀 COMO FICARIA O HISTÓRICO
feat: criação inicial da API de gerenciamento de clientes
feat: integração com ViaCEP usando OpenFeign
feat: implementação do CRUD de clientes
fix: correção da serialização JSON
docs: adiciona documentação Swagger
test: adiciona testes unitários

#  📌 Futuras melhorias

Autenticação JWT
Docker
PostgreSQL
Testes de integração
Paginação
Logs estruturados
Deploy cloud
CI/CD com GitHub Actions

# 👩‍💻 Desenvolvido por

Maria Correia

📄 Licença

Este projeto foi desenvolvido para fins educacionais e de portfólio.
