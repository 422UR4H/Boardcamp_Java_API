# Boardcamp_API (Java)

A RESTful API built in Java for provide a online system to rent board games.

## Description

This is my first small complete Web Service built in Java, with automated tests (unit and integration), which uses Hibernate ORM for database queries, to complete the last challenge proposed in Driven Education's Object Orientation Acceleration with Java.

With this challenge, I complete the minimum required to begin dedicating myself almost exclusively to rebuilding the big project: [GxG Batalhas: Integrado](https://github.com/422UR4H/GxG-Batalhas-Integrado)

For the even bigger project: [Hunter X Hunter RPG Environment](https://github.com/422UR4H/HxH_RPG_Environment_Java_API)

## Demo

### [Boardcamp API Link Deployed on Render](https://boardcamp-java-api.onrender.com)

<br />

## Quick start

Clone the repository, prepare the database and run the application locally in development mode.

Create a .env file following the .env.example to connect the server to a database.

```bash
DB_URL=jdbc:postgresql://localhost:5432/db_name # local (or remote) database link
DB_USERNAME=postgres # database username
DB_PASSWORD=postgres # database password
```

A local database or a deployed database can be used.

Finally, run in your favorite IDE:

## Usage

### How it works?

Owns the entities: `customer`, `game` and `rental`.

The characteristics of these entities are in `src/main/java/com/boardcamp/api/models`.

And the DTOs of the entities are in `src/main/java/com/boardcamp/api/dtos`.

### Routers:

- GET `/health`: To get API state

- GET `/customers/:id`: To get a customer by id

- POST `/customers`: To create a customer with body:

```yml
{
  "name": "string",
  "cpf": "cpf string" # length must be 11 characters
}
```

- PUT `/customers/:id`: To update a customer by id with the same body as above

- GET `/games`: To get all games

- GET `/games/:id`: To get a game by id

- POST `/games`: To create a game with body:

```yml
{
  "name": "string",
  "image": "url string",
  "stockTotal": number, # greater than 0
  "pricePerDay": number # greater than 0
}
```

- GET `/rentals`: To get all rentals with customer and game rented

- POST `/rentals`: To create a rental with body (numbers greater than 0):

```yml
{
  "customerId": number,
  "gameId": number,
  "daysRented": number
}
```

- PUT `/rentals/:id/return`: To finalize a rental by id that has no yet been finalized and calculate delay fee (if exists)

If the structure is not respected, a 400 error is returned.

# Technologies used

For this project, I used:

- Java (version 17.0.9);
- Spring Boot (version 3.2.2);
- Maven;
- JUnit;
- Hibernate;
- Spring Data JPA;
- SonarLint;
- Lombok;

## Tests

### Manual

The entire route structure is ready in a thunderClient collection (thunder-collection-boardcamp.json) for manual testing.

### Automatized

A local database is recomended, but a deployed database can also be used.

Is comming...
