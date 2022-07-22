package com.stock.Trade;

import com.stock.UserPortfolio.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Map;

@RestController
public class TradesController {

    @Autowired
    TradeService tradeService;

    @PostMapping("api/addTrade")
    public void addTrade(@RequestBody Map<String, String> payload) {
        System.out.println("code " + payload.get("cost"));
        System.out.println("symbol " + payload.get("symbol"));
        System.out.println("date " + payload.get("date"));

        TradeEntity trade = TradeEntity.builder().cost(Integer.valueOf(payload.get("cost"))).build();

        tradeService.addTrade(trade);
    }
}
