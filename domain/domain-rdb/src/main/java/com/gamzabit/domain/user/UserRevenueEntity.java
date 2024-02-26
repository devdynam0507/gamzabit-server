package com.gamzabit.domain.user;

import com.gamzabit.domain.common.EntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_revenue")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserRevenueEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalPurchasePrice;
    private Long totalEvaluationPrice;
    private Long totalProfit;
    private Long totalRevenueRate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity user;
}
