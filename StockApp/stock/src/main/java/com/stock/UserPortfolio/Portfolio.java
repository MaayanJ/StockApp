package com.stock.UserPortfolio;

import com.stock.Trade.TradeEntity;
import com.stock.User.UserEntity;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "portfolios", schema = "public")
@Builder
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String csvUpload;


    @CreationTimestamp
    private Date date;

//    private Long userId;

//    @OneToOne
    private long userId;

    @OneToMany
    private Set<TradeEntity> trades;

}
