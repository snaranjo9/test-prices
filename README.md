# Test-Prices
BCNC - Inditex Test Prices project with Spring Boot, Maven &amp; Java 11

## Technologies

   * Java 11
   * Spring Boot 2.7.8
   * Maven version 3.6.3
   * H2 in memory
   * Liquibase
   * Hexagonal Architecture

## Console Run

    $ mvn clean spring-boot:run

## Intellij Idea run

    1. Run -> Edit Configurations -> Add New Configuration -> Application
    2. Add the project main class <<PricesApplication.java>> to the configuration and confirm, OK.
    3. Finally, select the created configuration and click run/debug.

## Postman

    The project counts with a Postman Collection meant to test the application.
    This collection is located in the next path: src/test/resources/postman/PRICES.postman_collection.json

## Swagger 

    The REST API implemented in this application counts with an API SWAGGER Contract.