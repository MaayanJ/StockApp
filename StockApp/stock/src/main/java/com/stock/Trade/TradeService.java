package com.stock.Trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    private TradeJpaRepository tradeJpaRepository;

    public TradeEntity findTradeById(Long id) {
        return tradeJpaRepository.findTradeEntitiesById(id);
    }

    public void addTrade(TradeEntity tradeEntity) {
        tradeJpaRepository.save(tradeEntity);
    }
}
