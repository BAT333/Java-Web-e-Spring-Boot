package com.ByteCard.api.Application.UserCase.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Domain.Entities.Card.Card;

public class RegisterCard {
    public final RepositoryCard repositoryCard;

    public RegisterCard(RepositoryCard repositoryCard) {
        this.repositoryCard = repositoryCard;
    }

    public Card register(Card card){
        return this.repositoryCard.registerCard(card);
    }
}
