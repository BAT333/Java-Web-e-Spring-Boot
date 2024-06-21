package com.ByteCard.api.Application.UserCase.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Domain.Entities.Client.model.DataUpdate;

public class UpdeteClient {
    public final RepositoryClient repositoryClient;

    public UpdeteClient(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    public Client update(Long id, DataUpdate client){
        return this.repositoryClient.updeteClient(id, client);
    }
}
