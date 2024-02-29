package com.gamzabit.domain.user;

import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetId;
import com.gamzabit.domain.asset.AssetPrice;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.order.OrderId;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserAsset;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user_freeze_asset")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserFreezeAssetEntity extends EntityBase {

    @EmbeddedId
    private UserFreezeAssetId id;

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
        AssetId assetId,
        OrderId orderId
    ) {
        this.txDate = LocalDateTime.now();
        if (userAsset.amount().number().isLessThan(freezePrice.number())) {
            throw new RuntimeException("유저 계좌의 금액이 작습니다.");
        }
        this.userId = user.id().longValue();
        this.assetId = assetId.longValue();
        this.orderId = orderId.longValue();
    }
}
