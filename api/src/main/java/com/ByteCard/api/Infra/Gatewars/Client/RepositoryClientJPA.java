package com.ByteCard.api.Infra.Gatewars.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Persistence.Client.ClientRepository;

import java.util.List;
import java.util.Optional;

public class RepositoryClientJPA implements RepositoryClient {
    private final ClientRepository repository;
    private final ClientEntityMapper entityMapper;

    public RepositoryClientJPA(ClientRepository repository, ClientEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public Client registerClient(Client client) {
        var cliententity = repository.save(entityMapper.toEntity(client));
        return entityMapper.toDomain(cliententity);
    }

    @Override
    public Client updeteClient(Long id, Client client) {
        var clientE = repository.findById(id);
        if(clientE.isPresent()) {
            var clientU = entityMapper.toDomain(clientE.get()).updete(client);
            var cliententity = repository.save(entityMapper.toEntity(clientU));
            return entityMapper.toDomain(cliententity);
        }
        return null;
    }

    @Override
    public Boolean ActiveOrDeleteClient(Long id) {
        var cliententity =  this.repository.findById(id);
        if(cliententity.isPresent()){
            var client = entityMapper.toDomain(cliententity.get());
            var ativeOrDelete =  client.ativeOrDelete(cliententity.get().getActives());
            repository.save(entityMapper.toEntity(client));
            return ativeOrDelete;
        }
        return null;
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        var client = this.repository.findByCpf(cpf);
        if(client.isPresent()){
            return client.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByCpfAndActivesTrue(String cpf) {
        var client = this.repository.findByCpfAndActivesTrue(cpf);
        if(client.isPresent()){
            return client.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Client> findByCpfAndActivesFalse(String cpf) {
        var client = this.repository.findByCpfAndActivesFalse(cpf);
        if(client.isPresent()){
            return client.map(entityMapper::toDomain);
        }
        return Optional.empty();    }

    @Override
    public List<Client> findAllByActivesTrue() {
        var client = this.repository.findAllByActivesTrue();
        return client.stream().map(entityMapper::toDomain).toList();
    }

    @Override
    public List<Client> findAllByActivesFalse() {
        var client = this.repository.findAllByActivesFalse();
        return client.stream().map(entityMapper::toDomain).toList();
    }
}
