package com.ByteCard.api.Application.UserCase.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Client.Client;

import java.util.List;
import java.util.Optional;

public class FindClient {
    public final RepositoryClient repositoryClient;

    public FindClient(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    public Optional<Client> findByCpf(String cpf){
        return this.repositoryClient.findByCpf(cpf);
    }
    public Optional<Client> findByCpfAndActivesTrue(String cpf){
        return this.repositoryClient.findByCpfAndActivesTrue(cpf);
    }
    public Optional<Client> findByCpfAndActivesFalse(String cpf){
        return this.repositoryClient.findByCpfAndActivesFalse(cpf);
    }
    public List<Client> findAllByActivesTrue(){
        return this.repositoryClient.findAllByActivesTrue();
    }
    public List<Client> findAllByActivesFalse(){
        return this.repositoryClient.findAllByActivesFalse();
    }

}
