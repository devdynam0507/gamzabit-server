package com.gamzabit.api.user.domain;

import java.time.LocalDateTime;

import com.gamzabit.api.common.domain.EntityBase;
import com.gamzabit.api.user.service.vo.User;
import com.gamzabit.api.user.service.dto.UserCreation;

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

    public static UserEntity toUser(UserCreation userCreation) {
        return UserEntity.builder()
            .email(userCreation.email())
            .password(userCreation.password())
            .nickname(userCreation.nickname())
            .build();
    }

    public User toUser() {
        return new User(id, email, password, nickname, deleted, deletedAt, getCreatedAt());
    }
}
