package com.ByteCard.api.Infra.Gatewars.Buy;

import com.ByteCard.api.Application.Gateways.RepositoryBuy;
import com.ByteCard.api.Domain.Entities.Buy.Buy;
import com.ByteCard.api.Infra.Persistence.Buy.BuyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryBuyJPA implements RepositoryBuy {
    private final BuyRepository repositoryBuy;
    private final BuyEntityMapper entityMapper;

    public RepositoryBuyJPA(BuyRepository repositoryBuy, BuyEntityMapper entityMapper) {
        this.repositoryBuy = repositoryBuy;
        this.entityMapper = entityMapper;
    }

    @Override
    public Buy registerBuy(Buy buy) {
        return null;
    }

    @Override
    public Boolean ActiveOrDelete(Long id) {
        var buyent =  this.repositoryBuy.findById(id);
        if(buyent.isPresent()){
            var buy = entityMapper.toDomain(buyent.get());
            var ativeOrDelete =  buy.ativeOrDelete(buyent.get().getActives());
            repositoryBuy.save(entityMapper.toEntity(buy));
            return ativeOrDelete;
        }
        return null;
    }

    @Override
    public List<Buy> findAllByActivesTrue() {
        var buyenti = this.repositoryBuy.findAllByActivesTrue();
        return buyenti.stream().map(entityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Buy> findAllByActivesFalse() {
        var buyenti = this.repositoryBuy.findAllByActivesFalse();
        return buyenti.stream().map(entityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Buy> findById(Long id) {
        var buy = this.repositoryBuy.findById(id);
        if(buy.isPresent()){
            return buy.map(entityMapper::toDomain);
        }
        return Optional.empty();
    }
}
