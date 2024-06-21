package com.ByteCard.api.Infra.Gatewars.Client;

import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Gatewars.Card.CardEntityMapper;
import com.ByteCard.api.Infra.Gatewars.EntityFactory;
import com.ByteCard.api.Infra.Persistence.Client.ClientEntity;

public class ClientEntityMapper implements EntityFactory<Client, ClientEntity> {


    @Override
    public Client toDomain(ClientEntity clientEntity) {
        return new Client(clientEntity.getId(), clientEntity.getName(), clientEntity.getCpf(), clientEntity.getEmail(), clientEntity.getTelephone(),clientEntity.getActives());
    }

    @Override
    public ClientEntity toEntity(Client client) {
        return new ClientEntity(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getTelephone(),client.getActives());
    }
}
