package com.ByteCard.api.Card.Services;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Card.Dados.DadosCadastroCard;
import com.ByteCard.api.Client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardService {
    public static Card createCard(Client client, DadosCadastroCard card) {
        BigDecimal numberCard = BigDecimal.valueOf(Cardrandom.numbersCard());
        LocalDate date = LocalDate.now().plusYears(4).plusMonths(6);
        Integer cvv = Integer.valueOf(Cardrandom.numbercvv());
        return new Card(null, numberCard, date, cvv, card.limit().abs(), false, card.cpf(), client);
    }

}
