package com.ByteCard.api.Application.UserCase.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Client.Client;

public class RegisterClient {
    public final RepositoryClient repositoryClient;

    public RegisterClient(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    public Client register(Client client){
        return this.repositoryClient.registerClient(client);
    }
}
