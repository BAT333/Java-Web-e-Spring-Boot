package com.ByteCard.api.Infra.Persistence.Client;

import com.ByteCard.api.Domain.Entities.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
    Optional<ClientEntity> findByCpf(String cpf);

    Optional<ClientEntity> findByCpfAndActivesTrue(String cpf);

    Optional<ClientEntity> findByCpfAndActivesFalse(String cpf);

    List<ClientEntity> findAllByActivesTrue();

    List<ClientEntity> findAllByActivesFalse();
}
