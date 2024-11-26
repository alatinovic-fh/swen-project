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
* Controller: Handles communication with the HTTP layer.
* Service: Contains business logic.
* Repository/Data: Manages data access.

Changes in one layer have minimal impact on others, e.g., switching the storage solution.

New features can easily be added by implementing additional controllers, services, or routes.  
Decoupling between layers facilitates scalability and maintainability.

Common functionalities (e.g., JSON parsing, error handling) are centralized in base classes or utility classes.  
Code can be reused efficiently in new modules.