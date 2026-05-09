# Projeto API

API REST desenvolvida em Java com Spring Boot para cadastro e remocao de produtos. O projeto usa Spring Web MVC para expor endpoints HTTP, Spring Data JPA para persistencia e MySQL como banco de dados.

## Visao geral

Esta aplicacao implementa uma estrutura simples em camadas:

- `Controller`: recebe as requisicoes HTTP e retorna as respostas da API.
- `Service`: concentra a regra de aplicacao e coordena as operacoes.
- `Repository`: acessa o banco de dados usando Spring Data JPA.
- `Model`: representa a entidade `Produto` mapeada para a tabela `produtos`.

O fluxo principal da aplicacao e:

1. O cliente envia uma requisicao para `/produto`.
2. O `ProdutoController` recebe a requisicao.
3. O controller chama o `ProdutoService`.
4. O service usa o `ProdutoRepository`.
5. O repository persiste ou remove os dados no MySQL.

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Estrutura do projeto

```text
projeto-api/
├── src/
│   ├── main/
│   │   ├── java/com/ogtech/projeto_api/
│   │   │   ├── controllers/
│   │   │   │   └── ProdutoController.java
│   │   │   ├── models/
│   │   │   │   └── Produto.java
│   │   │   ├── repositories/
│   │   │   │   └── ProdutoRepository.java
│   │   │   ├── services/
│   │   │   │   └── ProdutoService.java
│   │   │   └── ProjetoApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/ogtech/projeto_api/
│           └── ProjetoApiApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Entidade Produto

A entidade `Produto` e mapeada para a tabela `produtos`.

| Campo | Tipo Java | Descricao |
| --- | --- | --- |
| `id` | `long` | Identificador unico gerado automaticamente pelo banco |
| `nome` | `String` | Nome do produto |
| `preco` | `BigDecimal` | Preco do produto |
| `quantidade` | `int` | Quantidade disponivel em estoque |

Exemplo de JSON:

```json
{
  "nome": "Teclado mecanico",
  "preco": 199.90,
  "quantidade": 10
}
```

## Endpoints

### Criar produto

```http
POST /produto
```

Cria um novo produto no banco de dados.

Corpo da requisicao:

```json
{
  "nome": "Mouse gamer",
  "preco": 129.90,
  "quantidade": 15
}
```

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Mouse gamer",
  "preco": 129.90,
  "quantidade": 15
}
```

Exemplo com cURL:

```bash
curl -X POST http://localhost:8080/produto \
  -H "Content-Type: application/json" \
  -d '{"nome":"Mouse gamer","preco":129.90,"quantidade":15}'
```

### Remover produto

```http
DELETE /produto/{id}
```

Remove um produto pelo identificador.

Exemplo:

```bash
curl -X DELETE http://localhost:8080/produto/1
```

Resposta esperada:

```text
Produto Removido
```

## Configuracao do banco de dados

O projeto esta preparado para usar MySQL. As configuracoes ficam em `src/main/resources/application.properties` e podem ser sobrescritas por variaveis de ambiente.

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/estudo}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
spring.jpa.hibernate.ddl-auto=update
```

Variaveis disponiveis:

| Variavel | Valor padrao | Descricao |
| --- | --- | --- |
| `DB_URL` | `jdbc:mysql://localhost:3306/estudo` | URL de conexao com o MySQL |
| `DB_USERNAME` | `root` | Usuario do banco |
| `DB_PASSWORD` | vazio | Senha do banco |

Antes de executar a aplicacao, crie o banco de dados:

```sql
CREATE DATABASE estudo;
```

No Windows PowerShell, voce pode definir a senha do banco assim:

```powershell
$env:DB_PASSWORD="sua_senha"
```

No Linux/macOS:

```bash
export DB_PASSWORD="sua_senha"
```

## Como executar

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Depois de iniciar, a API ficara disponivel em:

```text
http://localhost:8080
```

## Como testar

Execute os testes com Maven:

```bash
./mvnw test
```

No Windows PowerShell:

```powershell
.\mvnw.cmd test
```

O teste atual (`contextLoads`) verifica se o contexto do Spring Boot consegue iniciar corretamente.

Durante os testes, o projeto usa H2 em memoria por meio de `src/test/resources/application.properties`. Assim, a suite de testes nao depende de um MySQL local em execucao.

## Observacoes tecnicas

- O `ProdutoRepository` estende `JpaRepository<Produto, Long>`, entao o projeto ja possui metodos prontos como `save`, `deleteById`, `findById` e `findAll`.
- A propriedade `spring.jpa.hibernate.ddl-auto=update` faz o Hibernate atualizar a estrutura da tabela conforme a entidade. Isso e pratico em desenvolvimento, mas em producao normalmente se recomenda usar migrations, como Flyway ou Liquibase.
- A senha do banco nao fica fixa no codigo. Use a variavel `DB_PASSWORD` para configurar esse valor localmente.

## Proximos passos sugeridos

- Adicionar endpoint para listar produtos (`GET /produto`).
- Adicionar endpoint para buscar produto por ID (`GET /produto/{id}`).
- Adicionar endpoint para atualizar produto (`PUT /produto/{id}`).
- Criar validacoes com Bean Validation.
- Melhorar o tratamento de erros para casos como produto inexistente.
- Adicionar testes para controller, service e repository.
