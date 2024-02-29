package com.gamzabit.domain.user;

import java.time.LocalDateTime;

import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.vo.User;
import com.gamzabit.domain.user.vo.UserCreation;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    @EmbeddedId
    private UserId id;
    private String nickname;
    private String email;
    private String password;
    private Boolean deleted;
    private LocalDateTime deletedAt;

    @Builder
    public UserEntity(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
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

    public User toUser() {
        return new User(id, email, password, nickname, deleted, deletedAt, getCreatedAt());
    }
}
