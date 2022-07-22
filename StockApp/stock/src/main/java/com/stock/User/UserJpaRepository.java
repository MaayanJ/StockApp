package com.stock.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
//    Optional<UserEntity> findById(Integer id);
    UserEntity findByUserName(String userName);

    boolean existsUserEntityByUserName(String userName);
}
