package com.ByteCard.api.Config.BuyConfig;

import com.ByteCard.api.Application.Gateways.RepositoryBuy;
import com.ByteCard.api.Application.UserCase.Buy.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Buy.FindBuy;
import com.ByteCard.api.Application.UserCase.Buy.RegisterBuy;
import com.ByteCard.api.Infra.Gatewars.Buy.BuyEntityMapper;
import com.ByteCard.api.Infra.Gatewars.Buy.RepositoryBuyJPA;
import com.ByteCard.api.Infra.Gatewars.Card.CardEntityMapper;
import com.ByteCard.api.Infra.Gatewars.Client.ClientEntityMapper;
import com.ByteCard.api.Infra.Persistence.Buy.BuyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuyConfig {

    @Bean
    public RepositoryBuyJPA buyJPA(BuyRepository repositoryBuy, BuyEntityMapper entityMapper){
        return new RepositoryBuyJPA(repositoryBuy,entityMapper);
    }
    @Bean
    public BuyEntityMapper buyEntityMapper (ClientEntityMapper clientEntityMapper, CardEntityMapper entityMapper){
        return new BuyEntityMapper(clientEntityMapper,entityMapper);
    }
    @Bean
    public ActivesOrDelete orDelete(RepositoryBuy repository){
        return new ActivesOrDelete(repository);
    }
    @Bean
    public FindBuy findBuy(RepositoryBuy repositoryBuy){
        return new FindBuy(repositoryBuy);
    }
    @Bean
    public RegisterBuy buy(RepositoryBuy repositoryBuy){
        return new RegisterBuy(repositoryBuy);
    }

}
