package com.stock.UserPortfolio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
public class PortfolioController {

    @Autowired
    PortfolioService portfolio;

    @PostMapping("api/buyTrade")
        //need to know trade id, user id, how many shares
        public Portfolio addTrade(@RequestBody Map<String, String> payload) {
            System.out.println("trade id " + payload.get("tradeId"));
            System.out.println("user id " + payload.get("userName"));
            System.out.println("shares " + payload.get("shares"));

            portfolio.addToPortfolio(Long.valueOf(payload.get("tradeId")), payload.get("userName"), Integer.valueOf(payload.get("shares")));
            return Portfolio.builder().build();
        }

    @PostMapping("api/sellTrade")
    //need to know trade id, user id, how many shares
    public void sellTrade(@RequestBody Map<String, String> payload) {

        portfolio.sellStocks(Long.valueOf(payload.get("tradeId")), payload.get("userName"), Integer.valueOf(payload.get("shares")));

    }

    @GetMapping("api/cvsread")
    public void cvsRead() {
        portfolio.cvsRead();
    }

}
