package com.ByteCard.api.Application.UserCase.Buy;

import com.ByteCard.api.Application.Gateways.RepositoryBuy;
import com.ByteCard.api.Domain.Entities.Buy.Buy;

public class RegisterBuy {
    public final RepositoryBuy repositoryBuy;

    public RegisterBuy(RepositoryBuy repositoryBuy) {
        this.repositoryBuy = repositoryBuy;
    }


    public Buy register(Buy buy){
       return this.repositoryBuy.registerBuy(buy);
    }
}
