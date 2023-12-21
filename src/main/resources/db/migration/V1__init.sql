CREATE TABLE MANAGER
(
    manager_id serial primary key,
    manager_name text,
    manager_phone_num text,
    manager_email text
);
INSERT INTO MANAGER (manager_name, manager_phone_num, manager_email)
VALUES
    ('Витя Перкосетов', '+7912375610', 'xanaxperkoset@mail.ru'),
    ('Лена Головач', '+79191213450', 'golovachelena@example.com'),
    ('Сева Прилучный', '+79956022589', 'sevapetrovich@mail.ru');


CREATE TABLE users
(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES ('admin', '{noop}123', true),
       ('user', '{noop}456', true);

CREATE TABLE authorities
(
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO authorities
VALUES ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');