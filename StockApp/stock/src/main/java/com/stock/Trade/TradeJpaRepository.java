package com.stock.Trade;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradeJpaRepository extends JpaRepository<TradeEntity,Long> {
    TradeEntity findTradeEntitiesById(Long id);
}