package com.ByteCard.api.Infra.Gatewars.Card;

import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Infra.Gatewars.Client.ClientEntityMapper;
import com.ByteCard.api.Infra.Gatewars.EntityFactory;
import com.ByteCard.api.Infra.Persistence.Card.CardEntity;

public class CardEntityMapper implements EntityFactory<Card, CardEntity> {
    private final ClientEntityMapper clientEntityMapper;

    public CardEntityMapper(ClientEntityMapper mapper) {
        this.clientEntityMapper = mapper;
    }


    @Override
    public Card toDomain(CardEntity cardEntity) {

        return new Card(cardEntity.getId(),cardEntity.getNumberCard(),cardEntity.getDate(), cardEntity.getCvv(), cardEntity.getLimit(),cardEntity.getActives(), cardEntity.getClientCpf(),clientEntityMapper.toDomain(cardEntity.getClientID()));
    }

    @Override
    public CardEntity toEntity(Card card) {
        return new CardEntity(card.getId(),card.getNumberCard(),card.getDate(), card.getCvv(), card.getLimit(),card.getActives(), card.getClientCpf(), clientEntityMapper.toEntity(card.getClientID()));
    }
}
