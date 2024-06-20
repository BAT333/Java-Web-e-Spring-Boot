package com.ByteCard.api.Infra.Controller.Client.modal;

import jakarta.validation.constraints.NotNull;

public record DataClientFindDTO(
        String name,

        String cpf,

        String email,

        String telephone,
        Boolean actives
) {
}
