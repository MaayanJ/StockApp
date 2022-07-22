package com.stock.UserPortfolio;

import com.stock.Trade.TradeEntity;
import com.stock.Trade.TradeJpaRepository;
import com.stock.Trade.TradeService;
import com.stock.User.UserEntity;
import com.stock.User.UserJpaRepository;
import com.stock.User.UserService;
import org.apache.catalina.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private TradeService trade;

    @Autowired
    private UserService findUser;
    public Portfolio addToPortfolio(Long tradeId, String userName, Integer shares) {
        Portfolio portfolio;
        TradeEntity tradeEntity = trade.findTradeById(tradeId);

        UserEntity user = findUser.getUser(userName);
        //if portfolio exists
        double budget  = user.getBudget();
        if((user.getBudget()-tradeEntity.getCost() * shares)>0) {
            user.setBudget(budget - tradeEntity.getCost() * shares);
            findUser.updateBudget(user);

            if (portfolioRepository.existsPortfolioByUserId(user.getId())) {
                portfolio = portfolioRepository.findPortfolioByUserId(user.getId());
                //if the trade already exits in portfolio
                boolean tradeExists = false;
                for (TradeEntity entry : portfolio.getTrades()) {
                    if (entry.getId() == tradeId) {
                        Integer currentShares = entry.getShares();
                        entry.setShares(currentShares + shares);
                        tradeExists = true;
                        break;
                    }
                }
                //if trade does not exist in the portfolio
                if (!tradeExists) {
                    portfolio.getTrades().add(tradeEntity);
                }
            } else {
                Set<TradeEntity> trades = new HashSet<>();
                tradeEntity.setShares(shares);
                trades.add(tradeEntity);
                portfolio = Portfolio.builder().userId(user.getId()).trades(trades).build();
                user.setPortfolio(portfolio);
                portfolioRepository.save(portfolio);
            }
        }

        else {
            //throw an 'insufficient funds' error
            System.out.println("You don't have enough funds for this transaction");
            portfolio = Portfolio.builder().build();
        }
        //error cases for user not found
        //error cases for stocks not found
        return portfolio;
    }


    public void sellStocks(Long tradeId, String userName, Integer shares) {
        Portfolio portfolio;
        TradeEntity tradeEntity = trade.findTradeById(tradeId);

        UserEntity user = findUser.getUser(userName);

        if (portfolioRepository.existsPortfolioByUserId(user.getId())) {
            portfolio = portfolioRepository.findPortfolioByUserId(user.getId());
//            Set<TradeEntity> trades = portfolio.getTrades();
            for (TradeEntity entry : portfolio.getTrades()) {
                if (entry.getId() == tradeId) {
                    Integer currentShares = entry.getShares();
                    if(currentShares-shares < 0) {
                        System.out.println("You don't have enough shares to sell from this stock. you have " + currentShares + " available");
                    } else {
                        System.out.println("I am here");
                        entry.setShares(currentShares - shares);
                        double budget = user.getBudget();
                        user.setBudget(budget + shares*tradeEntity.getCost());
                        findUser.updateBudget(user);
                        // remove trade from portfolio if 0 shares
                    }
                    break;
                }
            }
        }
    }

    public Portfolio findUserPortfolio(long userId) {
        Portfolio portfolios = portfolioRepository.findPortfolioByUserId(userId);
        System.out.println(portfolios);
        return portfolios;
    }

    public void cvsRead() {
        String fileName = "C:\\Users\\Maayan Janow\\Desktop\\StockApp\\stock\\src\\main\\resources\\static\\UserPortfolio.csv";
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        try(FileReader fileReader = new FileReader(fileName);
            CSVParser csvParser = new CSVParser(fileReader, csvFormat)) {

            for(CSVRecord csvRecord : csvParser) {
                String cost = csvRecord.get("cost");
                String date = csvRecord.get("date");
                String shares = csvRecord.get("shares");
                String symbol = csvRecord.get("symbol");

                System.out.println(cost + "," + date + "," + shares + "," + symbol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cvsWriteToDB() {

    }
}
