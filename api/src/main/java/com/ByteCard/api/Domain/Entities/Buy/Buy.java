package com.ByteCard.api.Domain.Entities.Buy;

import com.ByteCard.api.Domain.CategoryBuy;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Buy {
    private Long id;
    private BigDecimal value;
    private LocalDateTime date;
    private String institute;
    private CategoryBuy categoryBuy;
    private String numberCard;
    private Client clientId;
    private Card cardId;
    private Boolean actives = true;


    public Buy() {
    }

    public Buy(Long id, BigDecimal value, LocalDateTime date, String institute, CategoryBuy categoryBuy, String numberCard, Client clientId, Card cardId,Boolean actives) {
        Objects.requireNonNull(id,"ID NULL");
        this.id = id;
        this.value = value;
        this.date = date;
        this.institute = institute;
        this.categoryBuy = categoryBuy;
        this.numberCard = numberCard;
        this.clientId = clientId;
        this.cardId = cardId;
        this.actives = actives;
    }

    public Buy(BigDecimal value, LocalDateTime date, String institute, CategoryBuy categoryBuy, String numberCard, Client clientId, Card cardId) {
        this.valid(value,date,institute,categoryBuy,numberCard,clientId,cardId);
        this.value = value;
        this.date = date;
        this.institute = institute;
        this.categoryBuy = categoryBuy;
        this.numberCard = numberCard;
        this.clientId = clientId;
        this.cardId = cardId;
        this.actives = true;
    }

    private void valid(BigDecimal value, LocalDateTime date, String institute, CategoryBuy categoryBuy, String numberCard, Client clientId, Card cardId) {
        Objects.requireNonNull(value,"VALUE NULL");
        Objects.requireNonNull(date,"DATE NULL");
        Objects.requireNonNull(institute,"INSTITUTE NULL");
        Objects.requireNonNull(categoryBuy,"CATEGORY BUY NULL");
        Objects.requireNonNull(numberCard,"NUMBER CARD NULL");
        Objects.requireNonNull(clientId,"CLIENT NULL");
        Objects.requireNonNull(cardId,"CARD NULL");
        if(!date.equals(LocalDateTime.now())&&value.equals(BigDecimal.ZERO)){
           throw new RuntimeException("ERRO DATE OR VALUE INVALID");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public CategoryBuy getCategoryBuy() {
        return categoryBuy;
    }

    public void CategoryBuy(CategoryBuy categoryBuy) {
        this.categoryBuy = categoryBuy;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }

    public void setCategoryBuy(CategoryBuy categoryBuy) {
        this.categoryBuy = categoryBuy;
    }

    public Boolean getActives() {
        return actives;
    }

    public void setActives(Boolean actives) {
        this.actives = actives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buy buy = (Buy) o;
        return Objects.equals(id, buy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BuyEntity{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", institute='" + institute + '\'' +
                ", categoryBuy=" + categoryBuy +
                ", numberCard=" + numberCard +
                ", clientId=" + clientId +
                ", cardId=" + cardId +
                '}';
    }

    public Boolean ativeOrDelete(Boolean actives) {
        if(actives){
            return this.actives = false;
        }
        return this.actives = true;
    }
}
