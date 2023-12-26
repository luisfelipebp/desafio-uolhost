# API de Gerenciamento de Jogadores Baseado no Desafio Uol Host

Esta API oferece funcionalidades para o gerenciamento de jogadores, permitindo a recuperação, busca por ID, exclusão e criação de novos jogadores. Os jogadores são compostos por nome, email, telefone e pertencem a grupos como Vingadores ou Liga da Justiça. Desafio: https://github.com/uolhost/test-backEnd-Java

## Endpoints Disponíveis

### Recuperar todos os jogadores

- **Método:** GET
- **Endpoint:** `/jogador/todos`
- **Descrição:** Retorna todos os jogadores cadastrados no sistema.

### Recuperar jogador por ID

- **Método:** GET
- **Endpoint:** `/jogador/{id}`
- **Descrição:** Retorna um jogador específico com base no ID fornecido.

### Deletar jogador por ID

- **Método:** DELETE
- **Endpoint:** `/jogador/{id}`
- **Descrição:** Remove um jogador com base no ID fornecido.

### Criar um novo jogador

- **Método:** POST
- **Endpoint:** `/jogador`
- **Descrição:** Cria um novo jogador com os seguintes parâmetros no corpo da requisição:
  
```
{
  "nome": "Nome do Jogador",
  "email": "jogador@example.com",
  "telefone": "123456789",
  "grupo": "VINGADORES"
}
```
Grupo deve ser VINGADORES ou LIGA_DA_JUSTICA

Ao criar um novo jogador, uma API externa será utilizada para atribuir um codinome aleatório ao usuário. Utilizando o formato JSON para os vingadores e o formato XML para a liga da justiça.

## Configuração Padrão

- **URL Base:** `http://localhost:8080`
- **Banco de Dados:** H2 (em memória)

## Requisitos

Certifique-se de ter o Java e as dependências do projeto instaladas para executá-lo.

## Executando o Projeto

1. Clone o repositório.
2. Configure as dependências e o ambiente de acordo com o arquivo de configuração fornecido.
3. Execute a aplicação.
4. Use as rotas mencionadas acima para interagir com a API.

