CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    stack VARCHAR(255),
    deck VARCHAR(255),
    coins INT DEFAULT 20,
    bio VARCHAR(255),
    image VARCHAR(255),
    stats INT DEFAULT 100
    );

--TODO change database Type