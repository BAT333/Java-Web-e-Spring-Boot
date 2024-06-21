package com.ByteCard.api.Domain.Entities.Client;

import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.model.DataUpdate;

import java.util.Objects;
import java.util.Set;

public class Client {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String telephone;
    private Boolean actives = true;
    private Set<Card> cards;

    public Client() {
    }

    public Client(Long id, String name, String cpf, String email, String telephone, Boolean actives) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telephone = telephone;
        this.actives = actives;

    }

    public Client(String name, String cpf, String email, String telephone) {
        this.valid(name,cpf,email,telephone);
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telephone = telephone;
        this.actives = true;
    }

    private void valid(String name, String cpf, String email, String telephone) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(cpf);
        Objects.requireNonNull(email);
        Objects.requireNonNull(telephone);
        if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))throw new RuntimeException("FORMAT CPF INVALID");
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))throw new RuntimeException("FORMAT EMAIL INVALID");
        if(telephone.length()<9)throw new RuntimeException("FORMAT TELEPHONE INVALID");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getActives() {
        return actives;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public void setActives(Boolean actives) {
        this.actives = actives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(cpf, client.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", actives=" + actives +
                '}';
    }

    public Client updete(DataUpdate client) {
        if(client.name() != null ){
            this.name = client.name();
        }
        if(client.cpf() != null ){
            this.cpf = client.cpf();
        }
        if(client.email() != null ){
            this.email = client.email();
        }
        if(client.telephone() != null ){
            this.telephone = client.telephone();
        }
        return this;
    }
    public void registerCard(Card card){
        card.setClientID(this);
        this.cards.add(card);
    }

    public Boolean ativeOrDelete(Boolean actives, Set<Card> card) {
        if(actives){
            return this.actives = false;
        }
        return this.actives = true;
    }
}
