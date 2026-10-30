# Pokedex API
 
API REST em Java (Spring Boot) para registrar pokemons capturados. Os dados ficam em um banco Oracle, na tabela `ddd_pokemons`.
 
## API publicada
 
A API está no ar no Render, no endereço base `https://pokedexapi-lnz2.onrender.com`. A lista completa fica em:
 
```
https://pokedexapi-lnz2.onrender.com/pokedex
```
 
Na hospedagem gratuita a API dorme quando fica parada, então a primeira resposta pode demorar cerca de um minuto. Os exemplos abaixo usam `http://localhost:8080`; para usar a versão publicada, troque pelo endereço base acima.
 
 
 Regras de validação:
 
* `nome` e `categoria`: obrigatórios, não podem ser vazios.
* `altura` e `peso`: números maiores que zero.
* `dataDeCaptura`: formato `AAAA-MM-DD`, não pode estar no futuro.
* `codigo`: gerado pelo banco. Não envie no cadastro. Na edição, vale o código da URL.
## Endpoints
 
| Método | Rota | Descrição | Respostas |
|---|---|---|---|
| GET | `/pokedex` | Lista todos os pokemons | 200 |
| GET | `/pokedex/{codigo}` | Busca um pokemon pelo código | 200, 404 |
| POST | `/pokedex` | Cadastra um pokemon | 201, 400 |
| PUT | `/pokedex/{codigo}` | Atualiza um pokemon | 200, 400, 404 |
| DELETE | `/pokedex/{codigo}` | Exclui um pokemon | 204, 404 |

## CORS
 
A API libera requisições de qualquer origem (`CorsFilter`), então um front em outro endereço, como `http://localhost:3000`, consegue consumi-la direto pelo navegador. Em produção, vale restringir a origem ao domínio do seu front.
 
## Erros comuns
 
* Erro 500 com a mensagem "The url cannot be null": a variável `DB_URL` não foi definida ao iniciar a API.
* Erro 500 ao conectar: confira usuário, senha, URL e se você tem acesso à rede do banco.
* Erro 400: algum campo não atende às regras de validação acima.
 
