package com.ByteCard.api.Application.UserCase.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;


import java.util.List;
import java.util.Optional;

public class FindCard {
    public final RepositoryCard repositoryCard;
    public final RepositoryClient repositoryClient;

    public FindCard(RepositoryCard repositoryCard, RepositoryClient repositoryClient) {
        this.repositoryCard = repositoryCard;
        this.repositoryClient = repositoryClient;
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
    public Optional<Client> findById(Long id){
        return this.repositoryClient.findById(id);
    }
    public List<Card> findByCard(Client client){
        return this.repositoryCard.findByClient(client);
    }

}
