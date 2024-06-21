package com.ByteCard.api.Infra.Controller.Card.modal;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataCardDTO(
        @NotNull
        BigDecimal limit
) {
}
