# MUSIC SALE

A `Vinyl BlueStore` é uma API para consulta e vendas de discos com cashback na compra.  
A base da dados é populada ao iniciar a aplicação consumindo a API do `Spotify`.  
Os discos são divididos em quatro tipos de generos `POP, MPB, CLASSICO, ROCK`

## ENTRADAS DA API
A `Vinyl BlueStore` disponibiliza algumas entradas para o sistema
>1. Consulta (**GET**) dos discos cadastrados, url de acesso (***/album***)
>2. Consulta (**GET**) de disco por id, url de acesso (***/album/{id}***)
>3. Consulta (**GET**) dos discos por genero, url de acesso (***/album/genre/{genre}***)
>4. Consulta (**GET**) das vendas por id, url de acesso (***/order/{id}***)
>5. Consulta (**GET**) das vendas por intervalo de data, url de acesso (***/order/date***)
>6. Consulta (**POST**) da venda, url de acesso (***/order/register***)

##Tecnologias Utilizadas
- Spring Boot
- MySQL
- Swagger
- JPA
- Hibernate
- Docker
- JUnit5
- Mockk

## Swagger
Para testar a aplicação com o `Swagger` basta estar com o projeto executando e acessar a url `/swagger-ui.html`
