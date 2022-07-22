package com.stock.User;

import com.stock.UserPortfolio.Portfolio;
import com.stock.UserPortfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

//    @Autowired
//    PortfolioService portfolioService;

    public void saveUser(UserEntity userEntity) {
        System.out.println("saving to repo......." + userEntity.getId());

        //error handling
        if(!userJpaRepository.existsUserEntityByUserName(userEntity.getUserName())) {
            userEntity.setBudget(100000.00);
            userJpaRepository.save(userEntity);
        } else {
            System.out.println("user with the same user name already exists");
        }

    }

    public UserEntity getUser(String userName) {
        System.out.println("user name " + userName);
        System.out.println("found " + userJpaRepository.findByUserName(userName));
        UserEntity user = userJpaRepository.findByUserName(userName);

       return user;

    }

    public void updateBudget(UserEntity user) {
        userJpaRepository.save(user);
    }

    public void updatePortfolio(UserEntity user, Portfolio portfolio) {
        user.setPortfolio(portfolio);
        userJpaRepository.save(user);
    }

    //delete user
    public void deleteUser(String userName) {
        Long id = userJpaRepository.findByUserName(userName).getId();
        userJpaRepository.deleteById(id);
    }
    //update user
}
