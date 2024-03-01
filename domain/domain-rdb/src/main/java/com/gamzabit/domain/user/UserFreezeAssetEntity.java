package com.gamzabit.domain.user;

import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_freeze_asset")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserFreezeAssetEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AssetAmount freezeAmount;

    private LocalDateTime txDate;

    private Long userId;
    private Long assetId;
    private Long orderId;

    public void freeze(
        AssetPrice freezePrice,
        UserAsset userAsset,
        User user,
        Long assetId,
        Long orderId
    ) {
        this.txDate = LocalDateTime.now();
        if (userAsset.amount().number().isLessThan(freezePrice.number())) {
            throw new RuntimeException("유저 계좌의 금액이 작습니다.");
        }
        this.userId = user.id();
        this.assetId = assetId;
        this.orderId = orderId;
    }
}
