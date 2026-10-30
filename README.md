# 🐾 AdotoPet — API REST para Adoção de Animais

O **AdotoPet** é uma API REST desenvolvida com Java e Spring Boot para gerenciar animais disponíveis para adoção, manter o cadastro de adotantes e administrar processos de adoção.

O projeto tem como objetivo aplicar conceitos de desenvolvimento backend em um cenário prático, com foco em organização do código, persistência de dados, relacionamentos entre entidades e implementação de regras de negócio.

## 🚀 Tecnologias

- **Java** — linguagem de programação.
- **Spring Boot** — estrutura principal da aplicação.
- **Spring Data JPA** — persistência e acesso aos dados.
- **Hibernate** — mapeamento objeto-relacional (ORM).
- **MySQL** — banco de dados relacional.
- **Flyway** — versionamento e gerenciamento de migrações do banco de dados.
- **Maven** — gerenciamento de dependências e build.
- **Git e GitHub** — controle de versão e hospedagem do código.

## ✨ Funcionalidades

### 🐶 Gerenciamento de animais
- Cadastro de animais para adoção.
- Consulta dos animais cadastrados.
- Atualização e exclusão de registros.
- Classificação por espécie, porte, sexo e status.
- Controle da disponibilidade para adoção.

### 👤 Gerenciamento de adotantes
- Cadastro de adotantes.
- Consulta, atualização e exclusão de registros.
- Validação de dados cadastrais.
- Restrição de CPF duplicado.

### 📋 Gerenciamento de adoções
- Registro de processos de adoção.
- Associação entre animais e adotantes.
- Controle de status do processo de adoção.
- Aprovação e cancelamento de adoções.
- Validação das regras de negócio para impedir adoções incompatíveis com o estado atual do animal.

## 🏗️ Organização do projeto

A aplicação está organizada em camadas para separar as responsabilidades e facilitar a manutenção do código.

- **Entities:** representação das entidades persistidas no banco de dados.
- **DTOs:** objetos utilizados para transportar dados nas requisições e respostas da API.
- **Repositories:** acesso e operações de persistência de dados.
- **Services:** implementação das regras de negócio.
- **Controllers:** gerenciamento dos endpoints HTTP.
- **Enums:** definição de valores fixos para espécies, portes, status e outras classificações.

## 🗄️ Banco de dados

O projeto utiliza MySQL para armazenar os dados e Flyway para versionar as alterações na estrutura do banco.

Os relacionamentos entre as entidades permitem associar os adotantes aos processos de adoção e controlar quais animais estão disponíveis ou já possuem uma adoção registrada.

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java JDK compatível com a versão configurada no projeto.
- MySQL Server.
- Maven.

### 1. Clone o repositório

```bash
git clone https://github.com/vtoorzDev/adotopet.git
```

### 2. Acesse a pasta do projeto

```bash
cd adotopet
```

### 3. Configure o banco de dados

Crie um banco de dados MySQL chamado `adotopet` e configure as credenciais de conexão no arquivo `application.properties`.

Não compartilhe suas credenciais pessoais ou senhas no repositório.

### 4. Execute a aplicação

No terminal, dentro da pasta do projeto, execute:

```bash
mvn spring-boot:run
```

O Flyway será responsável por executar as migrações configuradas durante a inicialização da aplicação.

> **Observação:** os comandos e requisitos devem corresponder à configuração atual do projeto. Caso o Maven não esteja instalado ou configurado no sistema, utilize o Maven Wrapper, se disponível no repositório.

## 🎯 Objetivos de desenvolvimento

O AdotoPet é um projeto de portfólio voltado à prática de desenvolvimento backend com Java, explorando:

- Construção de APIs REST.
- Operações CRUD.
- Validação de dados e regras de negócio.
- Relacionamentos entre entidades com JPA.
- Persistência de dados com MySQL.
- Migrações de banco de dados com Flyway.
- Organização de código em camadas.
- Controle de versão com Git.

## 📌 Status do projeto

**Em desenvolvimento.**

Novas funcionalidades e melhorias serão adicionadas conforme a evolução do projeto.

---

Desenvolvido como projeto de estudo e portfólio em **Java Backend**.
