package com.ByteCard.api.Infra.Gatewars.Client;

import com.ByteCard.api.Application.Gateways.RepositoryClient;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Domain.Entities.Client.model.DataUpdate;
import com.ByteCard.api.Infra.Gatewars.Card.CardEntityMapper;
import com.ByteCard.api.Infra.Persistence.Card.CardEntity;
import com.ByteCard.api.Infra.Persistence.Card.CardRepository;
import com.ByteCard.api.Infra.Persistence.Client.ClientEntity;
import com.ByteCard.api.Infra.Persistence.Client.ClientRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryClientJPA implements RepositoryClient {
    private final ClientRepository repository;
    private final ClientEntityMapper entityMapper;
    private final CardRepository cardRepository;
    private final CardEntityMapper cardEntityMapper;

    public RepositoryClientJPA(ClientRepository repository, ClientEntityMapper entityMapper, CardRepository cardRepository, CardEntityMapper cardEntityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
        this.cardRepository = cardRepository;
        this.cardEntityMapper = cardEntityMapper;
    }

    @Override
    public Client registerClient(Client client) {
        if(this.findByCpf(client.getCpf()).isPresent()) throw new RuntimeException("CPF INVALID");
        var cliententity = repository.save(entityMapper.toEntity(client));
        var card  = cardRepository.save(cardEntityMapper.toEntity(new Card(BigDecimal.valueOf(500L),cliententity.getCpf(),entityMapper.toDomain(cliententity))));
        return entityMapper.toDomain(cliententity);
    }

    @Override
    public Client updeteClient(Long id, DataUpdate client) {
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
            this.cards(cliententity.get());
            var client = entityMapper.toDomain(cliententity.get());
            var ativeOrDelete =  client.ativeOrDelete(cliententity.get().getActives(),null);
            repository.save(entityMapper.toEntity(client));
            return ativeOrDelete;
        }
        return null;
    }

    private void cards(ClientEntity client) {
       var cardlist =  this.cardRepository.findByClientID(client);
        var card = cardlist.stream().map(cardEntityMapper::toDomain).collect(Collectors.toSet());
        card.forEach(Card::delete);
        this.cardRepository.saveAll(card.stream().map(cardEntityMapper::toEntity).collect(Collectors.toList()));
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

    @Override
    public Optional<Client> findById(Long cpf) {
        var client = this.repository.findById(cpf);
        if(client.isPresent()){
            return client.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }
}
