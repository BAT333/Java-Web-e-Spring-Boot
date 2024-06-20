package com.ByteCard.api.Infra.Controller.Client.modal;

import jakarta.validation.constraints.NotNull;

public record DataClientDTO(
        @NotNull
        String name,
        @NotNull

        String cpf,
        @NotNull

        String email,
        @NotNull

        String telephone
) {
}
