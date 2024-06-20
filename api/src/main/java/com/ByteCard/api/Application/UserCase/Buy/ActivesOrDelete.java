package com.ByteCard.api.Application.UserCase.Buy;

import com.ByteCard.api.Application.Gateways.RepositoryBuy;

public class ActivesOrDelete {
    public final RepositoryBuy buy;

    public ActivesOrDelete(RepositoryBuy buy) {
        this.buy = buy;
    }
    public Boolean activesOrDelete(Long id){
      return this.buy.ActiveOrDelete(id);
    }
}
