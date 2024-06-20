package com.ByteCard.api.Config.ClientConfig;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Application.UserCase.Client.ActiveOrDeleteClient;
import com.ByteCard.api.Application.UserCase.Client.FindClient;
import com.ByteCard.api.Application.UserCase.Client.RegisterClient;
import com.ByteCard.api.Application.UserCase.Client.UpdeteClient;
import com.ByteCard.api.Infra.Gatewars.Client.ClientEntityMapper;
import com.ByteCard.api.Infra.Gatewars.Client.RepositoryClientJPA;
import com.ByteCard.api.Infra.Persistence.Client.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Bean
    public RepositoryClientJPA clientJPA (ClientRepository repository, ClientEntityMapper entityMapper){
        return new RepositoryClientJPA(repository,entityMapper);
    }
    @Bean
    public ClientEntityMapper clientEntityMapper(){
        return new ClientEntityMapper();
    }
    @Bean
    public ActiveOrDeleteClient activeOrDeleteClient(RepositoryClient repositoryClient){
        return new ActiveOrDeleteClient(repositoryClient);
    }
    @Bean
    public FindClient findClient (RepositoryClient repositoryClient){
        return new FindClient(repositoryClient);
    }
    @Bean
    public RegisterClient registerClient(RepositoryClient repositoryClient){
        return new RegisterClient(repositoryClient);
    }
    @Bean
    public UpdeteClient updeteClient(RepositoryClient repositoryClient){
        return new UpdeteClient(repositoryClient);
    }
}
