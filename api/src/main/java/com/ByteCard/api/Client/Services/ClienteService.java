package com.ByteCard.api.Client.Services;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Card.Services.Cardrandom;
import com.ByteCard.api.Client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteService {


    public static Card creationCard(Client clients) {
        Integer cvv = Integer.valueOf(Cardrandom.numbercvv());
        BigDecimal numberCard = BigDecimal.valueOf(Cardrandom.numbersCard());
        LocalDate date = LocalDate.now().plusYears(4).plusMonths(6);
       return new Card(null, numberCard, date, cvv, BigDecimal.ZERO, false, clients.getCpf(), clients);
    }
}
