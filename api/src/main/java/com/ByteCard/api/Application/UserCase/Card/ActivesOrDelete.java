package com.ByteCard.api.Application.UserCase.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;

public class ActivesOrDelete {
    public final RepositoryCard repositoryCard;

    public ActivesOrDelete(RepositoryCard repositoryCard) {
        this.repositoryCard = repositoryCard;
    }
    public Boolean activeOrDelete(Long id){
        return this.repositoryCard.ActivesOrDelete(id);
    }
}
