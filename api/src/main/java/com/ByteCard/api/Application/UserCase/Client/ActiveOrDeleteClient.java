package com.ByteCard.api.Application.UserCase.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;

public class ActiveOrDeleteClient {
    public final RepositoryClient repositoryClient;

    public ActiveOrDeleteClient(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }
    public Boolean activeOrDelete(Long id){
        return this.repositoryClient.ActiveOrDeleteClient(id);
    }
}
