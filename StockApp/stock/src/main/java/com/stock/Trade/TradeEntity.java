package com.stock.Trade;

import com.stock.User.UserEntity;
import com.stock.UserPortfolio.Portfolio;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "trades", schema = "public")
@Builder
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private java.sql.Date date;

    private String symbol;

    private Integer shares;

    private Integer cost;

}
