package com.ByteCard.api.Application.UserCase.Buy;

import com.ByteCard.api.Application.Gateways.RepositoryBuy;
import com.ByteCard.api.Domain.Entities.Buy.Buy;

import java.util.List;
import java.util.Optional;

public class FindBuy {
    public final RepositoryBuy repositoryBuy;

    public FindBuy(RepositoryBuy repositoryBuy) {
        this.repositoryBuy = repositoryBuy;
    }
    public List<Buy> findAllByActivesTrue(){
        return this.repositoryBuy.findAllByActivesTrue();
    }
    public List<Buy> findAllByActivesFalse(){
        return this.repositoryBuy.findAllByActivesFalse();
    }
    public Optional<Buy> findById(Long id){
        return this.repositoryBuy.findById(id);
    }
}
