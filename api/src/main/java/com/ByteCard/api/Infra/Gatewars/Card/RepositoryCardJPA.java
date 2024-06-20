package com.ByteCard.api.Infra.Gatewars.Card;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Infra.Persistence.Card.CardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryCardJPA implements RepositoryCard {
    private final CardRepository repositoryCard;
    private final CardEntityMapper entityMapper;

    public RepositoryCardJPA(CardRepository repositoryCard, CardEntityMapper entityMapper) {
        this.repositoryCard = repositoryCard;
        this.entityMapper = entityMapper;
    }

    @Override
    public Card registerCard(Card card) {
        var cardentity = repositoryCard.save(entityMapper.toEntity(card));
        return entityMapper.toDomain(cardentity);
    }

    @Override
    public Card updeteCard(Long id, Card card) {
        var cardE = this.repositoryCard.findById(id);
        if(cardE.isPresent()) {
            var cardU = entityMapper.toDomain(cardE.get()).updete(card);
            var cardentity = repositoryCard.save(entityMapper.toEntity(cardU));
            return entityMapper.toDomain(cardentity);
        }
        return null;
    }

    @Override
    public Boolean ActivesOrDelete(Long id) {
        var cardent =  this.repositoryCard.findById(id);
        if(cardent.isPresent()){
            var card = entityMapper.toDomain(cardent.get());
            var ativeOrDelete =  card.ativeOrDelete(cardent.get().getActives());
            repositoryCard.save(entityMapper.toEntity(card));
            return ativeOrDelete;
        }
        return null;
    }

    @Override
    public Optional<Card> findByIdAndActivesTrue(Long id) {
        var cardEnti = this.repositoryCard.findByIdAndActivesTrue(id);
        if(cardEnti.isPresent()){
            return cardEnti.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Card> findByIdAndActivesFalse(Long id) {
        var cardEnti = this.repositoryCard.findByIdAndActivesFalse(id);
        if(cardEnti.isPresent()){
            return cardEnti.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAllByActivesTrue() {
        var cardEnti = this.repositoryCard.findAllByActivesTrue();
        return cardEnti.stream().map(entityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Card> findAllByActivesFalse() {
        var cardEnti = this.repositoryCard.findAllByActivesFalse();
        return cardEnti.stream().map(entityMapper::toDomain).collect(Collectors.toList());
    }
}
