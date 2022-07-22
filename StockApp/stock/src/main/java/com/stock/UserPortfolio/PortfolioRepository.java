package com.stock.UserPortfolio;

import com.stock.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    Portfolio findPortfolioByUserId(long userId);

    boolean existsPortfolioByUserId(long userId);
}
