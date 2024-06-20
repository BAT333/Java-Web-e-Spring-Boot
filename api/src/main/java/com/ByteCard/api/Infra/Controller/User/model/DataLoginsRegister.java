package com.ByteCard.api.Infra.Controller.User.model;

import com.ByteCard.api.Domain.Entities.User.UserRoles;

import jakarta.validation.constraints.NotNull;

public record DataLoginsRegister(
        @NotNull
        String logins,
        @NotNull
        String password,
        UserRoles role
) {
}
