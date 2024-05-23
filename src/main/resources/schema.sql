-- DROP TABLE IF EXISTS requests, items, users;


CREATE TABLE IF NOT EXISTS users (
    id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(55) NOT NULL UNIQUE,
    number_phone VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
    id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(512) NOT NULL,
    in_stock BOOLEAN NOT NULL,
    owner_id BIGINT NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id),
    CONSTRAINT fk_items_owner_id FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS requests (
     id INT GENERATED ALWAYS AS IDENTITY NOT NULL,
     name VARCHAR(255) NOT NULL,
     description VARCHAR(512) NOT NULL,
     requester_id BIGINT NOT NULL,
     created DATE,
     CONSTRAINT pk_requests PRIMARY KEY (id),
     CONSTRAINT fk_requests_owner_id FOREIGN KEY (requester_id) REFERENCES users (id)
);

