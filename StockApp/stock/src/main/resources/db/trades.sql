CREATE TABLE trades
(
    id SERIAL PRIMARY KEY NOT NULL,
    date date,
    symbol varchar(100),
    cost bigint,
    shares numeric
);