package com.gamzabit.domain.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gamzabit.domain.asset.AssetEntity;
import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserCreation;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class UserEntity extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    @Embedded
    private UserCredentials userCredentials;
    private Boolean deleted;
    private LocalDateTime deletedAt;

    @Builder
    public UserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.userCredentials = new UserCredentials(email, password);
        this.deleted = false;
        this.deletedAt = null;
    }

    public static UserEntity of(UserCreation userCreation) {
        return UserEntity.builder()
            .email(userCreation.email())
            .nickname(userCreation.nickname())
            .password(userCreation.password())
            .build();
    }

    public UserAssetEntity createUserAsset(AssetEntity asset) {
        return UserAssetEntity.builder()
            .userId(id)
            .asset(asset)
            .amount(new AssetAmount(BigDecimal.ZERO))
            .build();
    }

    public void changePassword(String encryptedPassword) {
        userCredentials.setPassword(encryptedPassword);
    }

    public User toUser() {
        return new User(id, userCredentials, nickname, deleted, deletedAt, getCreatedAt());
    }
}
