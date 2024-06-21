package com.ByteCard.api.Application.Gateways;


import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Domain.Entities.Client.model.DataUpdate;

import java.util.List;
import java.util.Optional;

public interface RepositoryClient {
    Client registerClient(Client client);
    Client updeteClient(Long id, DataUpdate client);
    Boolean ActiveOrDeleteClient(Long id);
    Optional<Client> findByCpf(String cpf);
    Optional<Client>findByCpfAndActivesTrue(String cpf);
    Optional<Client> findByCpfAndActivesFalse(String cpf);
    List<Client> findAllByActivesTrue();
    List<Client> findAllByActivesFalse();
    Optional<Client> findById(Long id);



}
