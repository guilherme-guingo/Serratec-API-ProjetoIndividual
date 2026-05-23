# API de Gestão de Clientes e Veículos

Esta é uma API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de clientes e seus respectivos veículos. O sistema garante a consistência dos dados através de validações rigorosas, tratamento global de exceções e persistência em banco de dados relacional.

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5.14**
* **Spring Data JPA / Hibernate**
* **PostgreSQL** (Banco de Dados)
* **Springdoc OpenAPI (Swagger)** (Documentação)
* **Maven**

## ⚙️ Funcionalidades e Regras de Negócio

### Clientes
* Cadastro, listagem, busca por filtros (CPF ou Nome) e exclusão.
* **Validações:** Impede o cadastro de CPFs ou e-mails duplicados no banco de dados. Exige formatação correta de e-mail e telefones (10 a 12 dígitos).
* **Exclusão em Cascata:** Ao deletar um cliente, todos os veículos atrelados a ele são removidos automaticamente para manter a integridade do banco.

### Veículos
* Cadastro, listagem, busca por filtros (Placa, Marca ou Modelo), atualização e exclusão.
* **Validações:** Impede o cadastro de placas duplicadas. O ano do veículo deve ser maior ou igual a 1886. Valores devem ser estritamente positivos.
* **Regra de Venda:** Um veículo marcado como `vendido` exige obrigatoriamente um `valorVenda`. Um veículo não vendido não pode possuir um `valorVenda` registrado.

## 🛡️ Tratamento de Exceções

A API possui um `ControllerExceptionHandler` global que captura erros e devolve respostas padronizadas em JSON:
* **200 / 201 / 204:** Operações bem-sucedidas.
* **400 (Bad Request):** Disparado ao enviar dados inválidos, campos em branco ou quebra de regras de negócio.
* **404 (Not Found):** Disparado ao buscar, atualizar ou deletar IDs, CPFs ou Placas inexistentes.
* **409 (Conflict):** Disparado ao tentar cadastrar dados únicos já existentes (Ex: CPF ou Placa duplicada).
* **500 (Internal Server Error):** Erros internos mapeados.

## 📖 Documentação (Swagger)

A API possui documentação interativa gerada automaticamente pelo Swagger

Para acessar a interface gráfica do Swagger:
1. Rode a aplicação.
2. Acesse no navegador: `http://localhost:8081/swagger-ui/index.html`

## 🛠️ Como Executar o Projeto

1. Clone o repositório.
2. Certifique-se de ter o **Java 17** e o **PostgreSQL** instalados na sua máquina.
3. Crie um banco de dados no PostgreSQL
4. atualize suas credencias de acesso ao banco diretamente no arquivo `application.properties`
5. Atualize as dependências do Maven.
6. Execute a classe principal `TrabalhoIndividualApplication.java`.
7. O servidor iniciará na porta `8081`.
8. obs: Para fins didaticos, o banco foi configurado para ser criado do 0 após cada incialização, se quiser testar a persistencia, altere `spring.jpa.hibernate.ddl-auto=create` para `update` no `application.properties`

---
*Projeto desenvolvido como Trabalho Individual da disciplina de APIs do Serratec.*