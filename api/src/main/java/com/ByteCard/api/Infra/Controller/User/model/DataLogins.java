package com.ByteCard.api.Infra.Controller.User.model;

import com.ByteCard.api.Infra.Persistence.User.UserEntity;
import jakarta.validation.constraints.NotNull;

public record DataLogins(
        Long id,
        @NotNull
        String logins,
        @NotNull
        String password

) {
    public DataLogins(UserEntity user) {
        this(user.getId(), user.getLogins(), user.getPassword());
    }
}
