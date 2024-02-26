package com.gamzabit.domain.user;

import java.time.LocalDateTime;

import com.gamzabit.domain.common.EntityBase;
import com.gamzabit.domain.user.vo.User;

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

    public User toUser() {
        return new User(id, email, password, nickname, deleted, deletedAt, getCreatedAt());
    }
}