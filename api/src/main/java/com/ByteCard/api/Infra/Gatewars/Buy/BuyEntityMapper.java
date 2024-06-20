package com.ByteCard.api.Infra.Gatewars.Buy;

import com.ByteCard.api.Domain.Entities.Buy.Buy;
import com.ByteCard.api.Infra.Gatewars.Card.CardEntityMapper;
import com.ByteCard.api.Infra.Gatewars.Client.ClientEntityMapper;
import com.ByteCard.api.Infra.Gatewars.EntityFactory;
import com.ByteCard.api.Infra.Persistence.Buy.BuyEntity;

public class BuyEntityMapper implements EntityFactory<Buy, BuyEntity> {
    private final ClientEntityMapper clientEntityMapper;
    private final CardEntityMapper cardEntityMapper;

    public BuyEntityMapper(ClientEntityMapper clientEntityMapper,CardEntityMapper entityMapper) {
        this.cardEntityMapper= entityMapper;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Buy toDomain(BuyEntity entity){
        return new Buy(entity.getId(),entity.getValue(),entity.getDate(), entity.getInstitute(), entity.getCategoryBuy(), entity.getNumberCard(), clientEntityMapper.toDomain(entity.getClientId()),cardEntityMapper.toDomain(entity.getCardId()),entity.getActives());
    }
    @Override
    public BuyEntity toEntity(Buy buy){
        return new BuyEntity(buy.getId(),buy.getValue(),buy.getDate(), buy.getInstitute(), buy.getCategoryBuy(), buy.getNumberCard(), clientEntityMapper.toEntity(buy.getClientId()),cardEntityMapper.toEntity(buy.getCardId()),buy.getActives());
    }

}
