package com.ByteCard.api.Application.UserCase.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Domain.Entities.Card.Card;

public class UpdateCard {
    public final RepositoryCard repositoryCard;

    public UpdateCard(RepositoryCard repositoryCard) {
        this.repositoryCard = repositoryCard;
    }
    public Card update(Long id, Card card){
       return this.repositoryCard.updeteCard(id,card);
    }
}
