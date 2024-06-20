package com.ByteCard.api.Infra.Persistence.Buy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyRepository extends JpaRepository<BuyEntity,Long> {
    List<BuyEntity> findAllByActivesFalse();

    List<BuyEntity>  findAllByActivesTrue();
}
