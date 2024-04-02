# NTT Spring Movies

- Projeto desenvolvido em framework [Spring](https://spring.io/), versão 3.2.4
- Utilizadas bibliotecas Spring Web, Spring Devtools, Spring Validation, driver MySQL, Lombok, SpringDoc OpenAPI Starter WebMVC UI, ModelMapper e Apache Commons Lang.

O objetivo deste projeto foi desensvolver uma API rest utilizando o Spring e conectando-se a um bando de dados - neste caso, o MySQL.

## Como executar

Clone o projeto, execute `mvn install` para certificar de que as dependências foram corretamente instaladas e rode `mvn spring-boot:run` para executar o projeto em modo desenvolvimento. Os end-points tem como raiz a url base do Spring (normalmente `http://localhost:8080/`) mais alguma subrota.

- Migrations iniciais para o banco de dados estão disponíveis no arquivo `migrations.sql`;
- A documentação das rotas foi feita com Swagger e pode ser acessada em http://localhost:8080/swagger-ui/index.html;
- Além do Swagger, é possível importar os end-points que estão disponíveis no JSON `ntt-spring-movies.json` para os clientes http [Bruno](https://www.usebruno.com/) ou [Postman](https://www.postman.com/).

## TODO

- Implementar security;
- Revisar rotas sem uso e/ou mal implementadas.
