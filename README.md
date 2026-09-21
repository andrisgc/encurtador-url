# 🔗 Encurtador de URLs

Um projeto de encurtador de URLs, construído com **Java e Spring Boot** no Back-end e uma interface web minimalista em HTML/JS no Front-end.

Este projeto foi desenvolvido com foco em boas práticas de API REST, manipulação de banco de dados e integração Front-end/Back-end.

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3** (Spring Web, Spring Data JPA)
* **Banco de Dados H2** (In-Memory)
* **HTML5, CSS3 e JavaScript (Vanilla)** para a interface
* **Maven** para gestão de dependências

## ⚙️ Funcionalidades

- [x] Interface web para os utilizadores gerarem links curtos.
- [x] API REST que recebe uma URL longa e gera um código curto único.
- [x] Redirecionamento automático (`HTTP 302`) ao aceder ao código curto.
- [x] Contagem de cliques (rastreia quantas vezes o link foi acedido).

## 🛠️ Como executar o projeto localmente

```
git clone https://github.com/andrisgc/encurtador-url.git
cd encurtador-url
./mvnw spring-boot:run
```

Abra o navegador e aceda à interface web: http://localhost:8080/

## 📡 Endpoints da API

### 1. Encurtar uma URL
* **Rota:** `POST /encurtar`
* **Corpo da requisição (JSON):**
  {
  "urlOriginal": "https://www.exemplo.com/uma-url-muito-longa"
  }

* **Resposta de Sucesso (200 OK):** Retorna os dados da URL encurtada, incluindo o código gerado.

### 2. Redirecionar para a URL Original
* **Rota:** `GET /{codigoCurto}`
* **Comportamento:** Valida o código (ignorando ficheiros de sistema). Se encontrado, redireciona (`Status 302 FOUND`) para o site original. Caso contrário, retorna `404 Not Found`.

## 🗄️ Acesso ao Banco de Dados (H2 Console)
Como o projeto utiliza o banco de dados H2 em memória, você pode inspecionar as URLs guardadas em tempo real:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:encurtadordb`
* **User:** `sa`
* **Password:**