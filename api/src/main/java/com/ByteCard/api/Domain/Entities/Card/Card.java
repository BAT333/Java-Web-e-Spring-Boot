package com.ByteCard.api.Domain.Entities.Card;

import com.ByteCard.api.Domain.Entities.Client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Card {
    private Long id;
    private String numberCard;
    private LocalDate date = LocalDate.now().plusYears(4).plusMonths(6);
    private String cvv;
    private BigDecimal limit;
    private Boolean actives;
    private String clientCpf;

    private Client clientID;

    public Card() {
    }

    public Card(Long id, String numberCard, LocalDate date, String cvv, BigDecimal limit, Boolean actives, String clientCpf, Client clientID) {
        this.id = id;
        this.numberCard = numberCard;
        this.date = date;
        this.cvv = cvv;
        this.limit = limit;
        this.actives = actives;
        this.clientCpf = clientCpf;
        this.clientID = clientID;
    }

    public Card(Long id, String numberCard, String cvv, BigDecimal limit, String clientCpf, Client clientID) {

        this.id = id;
        this.numberCard = numberCard;
        this.date =  LocalDate.now().plusYears(4).plusMonths(6);
        this.cvv = cvv;
        this.limit = limit;
        this.actives = true;
        this.clientCpf = clientCpf;
        this.clientID = clientID;
    }

    public Card(BigDecimal limit, String clientCpf, Client clientID) {
        CardsConstructor.constructor(limit,clientCpf,clientID,this);
        this.date =  LocalDate.now().plusYears(4).plusMonths(6);
        this.limit = limit;
        this.actives = true;
        this.clientCpf = clientCpf;
        this.clientID = clientID;
    }

    public Card(BigDecimal limit) {
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public Boolean getActives() {
        return actives;
    }

    public void setActives(Boolean actives) {
        this.actives = actives;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public Client getClientID() {
        return clientID;
    }

    public void setClientID(Client clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) && Objects.equals(numberCard, card.numberCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberCard);
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", numberCard=" + numberCard +
                ", date=" + date +
                ", cvv=" + cvv +
                ", limit=" + limit +
                ", status=" + actives +
                ", clientCpf='" + clientCpf + '\'' +
                ", clientID=" + clientID +
                '}';
    }


    public Card updete(Card card) {
        if(card.limit != null){
            this.limit = card.limit;
        }
        return this;
    }

    public Boolean ativeOrDelete(Boolean actives) {
       if(clientID.getActives()){
           if(actives){
               return this.actives = false;
           }
           return this.actives = true;
       }
       throw new RuntimeException("NO EXIST CARD");
    }

    public void delete() {
        this.actives = false;
    }
}
