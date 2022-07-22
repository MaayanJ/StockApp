package com.stock.User;



import com.stock.Trade.TradeEntity;
import com.stock.UserPortfolio.Portfolio;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "users", schema = "public")
@Builder
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;

    @Column(name = "user_name")
    @ToString.Exclude
    private String userName;

    @ToString.Exclude
    @Column(columnDefinition = "numeric default '100000.00'")
    private double budget;

    @OneToOne(cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Portfolio portfolio;



}