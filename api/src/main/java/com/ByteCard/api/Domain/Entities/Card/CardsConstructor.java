package com.ByteCard.api.Domain.Entities.Card;


import com.ByteCard.api.Domain.Entities.Client.Client;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public interface CardsConstructor {
    static void constructor(BigDecimal limit, String clientCpf, Client clientID,Card card){
        Objects.requireNonNull(limit,"LIMIT NULL");
        Objects.requireNonNull(clientCpf,"CPF NULL");
        Objects.requireNonNull(clientID, "CLIENT ID NULL");
        Objects.requireNonNull(card,"CARD NULL");
        if(clientID.getActives())throw new RuntimeException("CPF INVALID");
        if(!clientCpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))throw new RuntimeException("FORMAT CPF INVALID");
        if(card.getCvv() !=null){
            if(!card.getCvv().isEmpty()||!card.getNumberCard().isEmpty()) throw new RuntimeException("CONSTRUCTOR REGISTER");

        }

        card.setCvv(numbersCard(3));
        card.setNumberCard(numbersCard(16));
    }

    private static String numbersCard(int num){
        StringBuilder numero = new StringBuilder();
        do {
            for (int i = 0; i < num; i++) {
                long numeros = ThreadLocalRandom.current().nextInt(9);
                numero.append(numeros);
            }
        } while (numero.length() != num);
        return numero.toString();

    }
}
