# Monster Trading Card Game - Latinovic


Das Programm ist eine REST-basierte Serveranwendung, die ein Trading- und Battle-System für ein Kartenspiel im Fantasy-Setting bereitstellt. Spieler können sich registrieren, Karten sammeln, Decks erstellen, gegeneinander antreten und ihre Statistiken einsehen.


## Github

```
git clone https://github.com/alatinovic-fh/swen-project
```

## Requirements
* Java: 21
* Docker

## How to run Database
```yaml
docker-compose up -d #detached mode
```

## Struktur
* Controller: Kommunikation mit der HTTP-Schicht.
* Service: Geschäftslogik.
* Repository/Daten: Datenzugriff.

Änderungen in einer Schicht beeinflussen andere kaum, z. B. Wechsel der * Speicherlösung.

Neue Features können durch zusätzliche Controller, Services oder Routen einfach ergänzt werden.
Entkopplung zwischen den Schichten erleichtert die Erweiterbarkeit.

Gemeinsame Funktionen (z. B. JSON-Verarbeitung, Fehlerbehandlung) sind in Basisklassen oder Utility-Klassen zentralisiert.
Code kann effizient in neuen Modulen wiederverwendet werden.