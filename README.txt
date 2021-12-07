# rdswithapirest

Descrição
----------

Esse projeto está associado ao curso da DIO - Digital Innovation One - utilizando Java para criar uma API rest. Uma API é dita Restful se ela segue o padrão REST, 
ou então, o modelo de maturidade de Richardson. Para isso, utilizamos nesta API básica módulos como: spring web e spring Hateoas.
O spring-web, é um módulo do framework spring, fornece uma séries de métodos e classes com estrutura rest para simplificar a criação de uma API. Enquanto isso,
o spring-hateoas possibilita a implementação do último nível de maturidade de Richardson. Dessa forma, poderemos aplicar links aos recurso e consequentemente, 
fornecemos um guia para navegabilidade do usuário dentro da API.

Neste projeto foi utilizado a versão 17 do Java.

Página para criar seu projeto spring-boot: https://start.spring.io/

Conteúdo
--------

MyFirstApi
-> Person
->PersonController
->PersonRepository

ApiRest
->Employee
->EmployeeController
->EmployeeRepository
->LoadBD
->EmployeeNotFoundException
->EmployeeNotFoundAdvice

Hateoas
->Controllers
->Repositories
->Exceptoins
->Entyties
->Swagger
->LoadBDHateoas


Cronograma do curso DIO
------------------------

Criando uma API REST Conectada ao Amazon RDS com Java

AULA 1. Introdução
ETAPA 1 - Banco de dados relacionais
ETAPA 2 - Relembrando principais conceitos de BD
ETAPA 4 - O que é computação em nuvem (Cloud Computing)?
ETAPA 5 - AWS - Visão geral

AULA 2. AWS RDS
ETAPA 1 - RDS e Aurora
ETAPA 2 - Instanciando um BD na AWS RDS
ETAPA 4 - Conectando a RDS com @configuration.properties
https://docs.spring.io/spring-boot/docs/1.3.0.M2/reference/html/boot-features-sql.html

AULA 3. Criando projeto básico com Spring Boot 
ETAPA 1 - O que é Spring, Spring Boot e Spring Initializr?
ETAPA 2 - Entendendo as dependências
ETAPA 3 -  API com Spring boot


AULA 4. API REST COM CRUD
ETAPA 1 - REST API E CRUD
ETAPA 2 - Abstração e modelagem de domínio
          utilizando UML


AULA 5. CONSTRUINDO API REST COM RDS AWS


AULA 6. API REST com HATEOAS
ETAPA 1 - Adicinando links aos métodos GET
ETAPA 2 - Criando collections para employee
ETAPA 3 - criando a classe order: com atributos descrição e status
ETAPA 4 - Criando OrderController com links

