# Monster Trading Card Game - Latinovic

The program is a REST-based server application that provides a trading and battle system for a fantasy-themed card game. Players can register, collect cards, build decks, compete against each other, and view their statistics.

## Github

```bash
git clone https://github.com/alatinovic-fh/swen-project
```

## Requirements
- Java: 21
- Docker

## How to run the database
```yaml
docker-compose up -d # detached mode
```

## Structure

In the root directory, Main.java starts the
application. The application directory contains the core logic and modules,
such as MonsterTradingCard.java (central game logic), controller
(e.g., UserController.java for user requests),
data (repositories like UserRepository.java),
entity (domain models like Card.java and User.java),
exception (custom exceptions like AuthenticationFailedException.java),
routing (route configuration through Route.java and Router.java),
service (e.g., UserService.java for business logic),
and util (e.g., PostgresConfig.java for database setup).
The server directory handles server logic, with Application.
java to start the server, RequestHandler.java for handling requests,
and Server.java for managing connections.
Additional subdirectories like http and util provide classes for
HTTP protocol and utility functions. The structure follows the MVC pattern,
effectively separating application logic, data access, error handling,
and server communication.

* Controller: Handles communication with the HTTP layer.
* Service: Contains business logic.
* Repository/Data: Manages data access.

Changes in one layer have minimal impact on others, e.g., switching the storage solution.

New features can easily be added by implementing additional controllers, services, or routes.  
Decoupling between layers facilitates scalability and maintainability.

Common functionalities (e.g., JSON parsing, error handling) are centralized in base classes or utility classes.  
Code can be reused efficiently in new modules.