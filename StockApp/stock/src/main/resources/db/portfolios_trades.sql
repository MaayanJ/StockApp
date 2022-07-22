CREATE TABLE portfolios_trades
(
    portfolio_id bigint not null,
    trade_id bigint not null,
    shares numeric,
    FOREIGN KEY (portfolio_id) REFERENCES portfolios(id),
    FOREIGN KEY (trade_id) REFERENCES trades(id),
    PRIMARY KEY (portfolio_id, trade_id)
);