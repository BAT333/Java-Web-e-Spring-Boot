package com.ByteCard.api.Infra.Controller.Card.modal;

import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataCard(
        Long id,
        String numberCard,
        LocalDate date ,
        String cvv,
        BigDecimal limit,
        Boolean actives,
        String clientCpf,
        Client clientID
) {
    public DataCard(Card card) {
        this(card.getId(), card.getNumberCard(), card.getDate()
                ,card.getCvv(), card.getLimit(),card.getActives()
                ,card.getClientCpf(),card.getClientID());
    }
}
