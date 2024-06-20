package com.ByteCard.api.Application.UserCase.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Domain.Entities.Card.Card;


import java.util.List;
import java.util.Optional;

public class FindCard {
    public final RepositoryCard repositoryCard;

    public FindCard(RepositoryCard repositoryCard) {
        this.repositoryCard = repositoryCard;
    }

    public Optional<Card>  findByIdAndActivesTrue(Long id){
        return this.repositoryCard.findByIdAndActivesTrue(id);
    }
    public Optional<Card>  findByIdAndActivesFalse(Long id){
        return this.repositoryCard.findByIdAndActivesFalse(id);
    }
    public List<Card> findAllByActivesFalse(){
        return this.repositoryCard.findAllByActivesFalse();
    }
    public List<Card> findAllByActivesTrue(){
        return this.repositoryCard.findAllByActivesTrue();
    }
}
