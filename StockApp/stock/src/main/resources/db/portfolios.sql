CREATE TABLE portfolios
(
    id SERIAL,
    csv_upload varchar(100),
    date timestamp NOT NULL,
    user_id bigint not null,
    trade_id bigint not null,
    PRIMARY KEY (id, user_id, trade_id)
);
