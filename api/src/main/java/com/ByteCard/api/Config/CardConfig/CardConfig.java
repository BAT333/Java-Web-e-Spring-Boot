package com.ByteCard.api.Config.CardConfig;

import com.ByteCard.api.Application.Gateways.RepositoryCard;
import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Application.UserCase.Card.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Card.FindCard;
import com.ByteCard.api.Application.UserCase.Card.RegisterCard;
import com.ByteCard.api.Application.UserCase.Card.UpdateCard;
import com.ByteCard.api.Infra.Gatewars.Card.CardEntityMapper;
import com.ByteCard.api.Infra.Gatewars.Card.RepositoryCardJPA;
import com.ByteCard.api.Infra.Gatewars.Client.ClientEntityMapper;
import com.ByteCard.api.Infra.Persistence.Card.CardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardConfig {
    @Bean
    public RepositoryCardJPA cardJPA(CardRepository repositoryCard, CardEntityMapper entityMapper,ClientEntityMapper clientEntityMapper){
        return new RepositoryCardJPA(repositoryCard,entityMapper, clientEntityMapper);
    }
    @Bean
    public CardEntityMapper cardEntityMapper (ClientEntityMapper mapper){
        return new CardEntityMapper(mapper);
    }
    @Bean
    public ActivesOrDelete activesOrDelete(RepositoryCard repositoryCard){
        return new ActivesOrDelete(repositoryCard);
    }
    @Bean
    public FindCard card (RepositoryCard repositoryCard, RepositoryClient repositoryClient){
        return new FindCard(repositoryCard, repositoryClient);
    }
    @Bean
    public RegisterCard registerCard(RepositoryCard repositoryCard){
        return new RegisterCard(repositoryCard);
    }
    @Bean
    public UpdateCard updateCard(RepositoryCard repositoryCard){
        return new UpdateCard(repositoryCard);
    }
}
