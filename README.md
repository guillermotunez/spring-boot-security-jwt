# Desafío con Spring Boot, Spring Security, PostgreSQL and JWT.

## Requisitos:

- Docker Desktop

## Ejecución con Docker(compose):

- git clone https://github.com/guillermotunez/spring-boot-security-jwt.git
- cd spring-boot-security-jwt
- ./mvnw clean package 
- ./compose-up.sh

## Pruebas sobre las funcionalidades existentes con Postman:

- usar la colección de Postman para probar las diferentes funcionalidades.
- Challenge.postman_collection_v2.1.json ubicada en la raiz del repositorio de github.
 
## Parado del proceso Docker(compose):

- ./compose-down.sh

## User Sing Up, Login and Authorization process.

![sequence-flow](sequence-flow.png)

## Spring Boot Server Architecture with Spring Security.

![architecture](architecture.png)
