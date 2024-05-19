-- DROP TABLE IF EXISTS bookings, items, users;


CREATE TABLE IF NOT EXISTS users (
    id INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(12) NOT NULL,
    numberphone VARCHAR(20),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS items (
    id INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(512) NOT NULL,
    inStock BOOLEAN NOT NULL ,
    owner_id BIGINT NOT NULL,
    request_id BIGINT NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id),
    CONSTRAINT fk_items_owner_id FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT fk_items_request_id FOREIGN KEY (request_id) REFERENCES request (id)

);

CREATE TABLE IF NOT EXISTS request (
       id INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
       description VARCHAR(512) NOT NULL,
       requestor_id BIGINT NOT NULL,
       created DATE,
       CONSTRAINT pk_request PRIMARY KEY (id),
       CONSTRAINT fk_request_requester_id FOREIGN KEY (requestor_id) REFERENCES users (id)
);