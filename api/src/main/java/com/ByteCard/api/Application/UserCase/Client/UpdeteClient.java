package com.ByteCard.api.Application.UserCase.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Client.Client;

public class UpdeteClient {
    public final RepositoryClient repositoryClient;

    public UpdeteClient(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    public Client update(Long id, Client client){
        return this.repositoryClient.updeteClient(id,client);
    }
}
