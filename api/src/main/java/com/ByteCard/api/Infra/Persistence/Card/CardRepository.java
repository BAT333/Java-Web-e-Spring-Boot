package com.ByteCard.api.Infra.Persistence.Card;

import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Infra.Persistence.Client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity,Long> {
    Optional<CardEntity> findByIdAndActivesTrue(Long id);

    Optional<CardEntity> findByIdAndActivesFalse(Long id);

    List<CardEntity> findAllByActivesTrue();

    List<CardEntity> findAllByActivesFalse();

    List<CardEntity> findByClientID(ClientEntity client);


}
