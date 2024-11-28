# Monster Trading Card Game - Latinovic

The program is a REST-based server application that provides a trading and battle
system for a fantasy-themed card game. Players can register, collect cards, build
decks, compete against each other, and view their statistics.
 
## Github

```bash
git clone https://github.com/alatinovic-fh/swen-project
```

## Requirements
- Java: 21
- Docker

## How to run the database
```yaml
docker compose up -d
```
### To create table
Run the init.sql on the database
```shell
#1.docker shell
docker exec -it mtcgdb sh
#2.login as root
su
#3.login postgres
psql -h localhost -p 5432 -U user -d db
#paste the sql here
```

## Structure

```
│   Main.java
│
├───application
│   │   MonsterTradingCard.java
│   │
│   ├───controller
│   │       Controller.java
│   │       UserController.java
│   │
│   ├───data
│   │       UserMemoryRepository.java
│   │       UserRepository.java
│   │
│   ├───entity
│   │       Card.java
│   │       CreatureType.java
│   │       User.java
│   │
│   ├───exception
│   │       AuthenticationFailedException.java
│   │       ControllerNotFoundException.java
│   │       InvalidBodyException.java
│   │       JsonParserException.java
│   │       UserAlreadyExistsException.java
│   │
│   ├───routing
│   │       Route.java
│   │       Router.java
│   │
│   ├───service
│   │       UserService.java
│   │
│   └───util
│           PostgresConfig.java
│
└───server
    │   Application.java
    │   RequestHandler.java
    │   Server.java
    │
    ├───http
    │       Method.java
    │       Request.java
    │       Response.java
    │       Status.java
    │
    └───util
            HttpRequestParser.java
            HttpResponseFormatter.java
            HttpSocket.java
            NoHttpStatusException.java


```


In the root directory, Main.java starts the
application. The application directory contains the core logic and modules,
such as MonsterTradingCard.java (central game logic), controller
(e.g., UserController.java for user requests),
data (repositories like UserRepository.java),
entity (like Card.java and User.java),
exception (custom exceptions like AuthenticationFailedException.java),
routing (route configuration through Route.java and Router.java),
service (e.g., UserService.java for business logic),
and util (e.g., PostgresConfig.java for database setup).
The server directory handles server logic,to start the server,
RequestHandler.java for handling requests,
and Server.java for managing connections.
Additional subdirectories like http and util provide classes for
HTTP protocol and utility functions for parsing Requests and Responses.

### Layer logic

* Controller: Handles communication with the HTTP layer.
* Service: Contains business logic.
* Repository/Data: Manages data access.

Changes in one layer have minimal impact on others, e.g., switching the storage solution.

New features can easily be added by implementing additional controllers, services, or routes.  
Decoupling between layers facilitates scalability and maintainability.

Common functionalities (e.g., JSON parsing, error handling) are centralized in base classes or utility classes.  
Code can be reused efficiently in new modules.