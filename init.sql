CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    stack VARCHAR(255),
    deck VARCHAR(255),
    coins INT DEFAULT 20,
    fullname VARCHAR(255),
    bio VARCHAR(255),
    image VARCHAR(255),
    stats INT DEFAULT 100
    );

--TODO change database Type

create table if not exists Packages (
                                        id serial primary key,
                                        card_1 varchar(255) not null,
    card_2 varchar(255) not null,
    card_3 varchar(255) not null,
    card_4 varchar(255) not null,
    card_5 varchar(255) not null,
    bought bool default false not null
    );

create table if not exists Cards (
                       username varchar(255),
                       package_id int references packages(id),
                       card_id varchar(255) primary key not null,
                       name varchar(255) not null,
                       damage float not null
);

