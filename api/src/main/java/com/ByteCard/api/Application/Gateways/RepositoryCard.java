package com.ByteCard.api.Application.Gateways;


import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;

import java.util.List;
import java.util.Optional;

public interface RepositoryCard {
    Card registerCard(Card card);
    Card updeteCard(Long id, Card card);
    Boolean ActivesOrDelete(Long id);
    Optional<Card> findByIdAndActivesTrue(Long id);
    Optional<Card> findByIdAndActivesFalse(Long id);
    List<Card> findAllByActivesTrue();
    List<Card> findAllByActivesFalse();
    List<Card> findByClient(Client client);


}
