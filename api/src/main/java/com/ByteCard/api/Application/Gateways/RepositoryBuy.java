package com.ByteCard.api.Application.Gateways;


import com.ByteCard.api.Domain.Entities.Buy.Buy;

import java.util.List;
import java.util.Optional;

public interface RepositoryBuy {
    Buy registerBuy(Buy buy);
    Boolean ActiveOrDelete(Long id);
    List<Buy> findAllByActivesTrue();
    List<Buy> findAllByActivesFalse();
    Optional<Buy> findById(Long id);

}
