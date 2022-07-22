CREATE TABLE users
(
    id SERIAL PRIMARY KEY NOT NULL,
    budget numeric,
    user_name varchar(100) UNIQUE
);
