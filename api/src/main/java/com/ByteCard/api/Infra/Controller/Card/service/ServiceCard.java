package com.ByteCard.api.Infra.Controller.Card.service;

import com.ByteCard.api.Application.UserCase.Card.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Card.FindCard;
import com.ByteCard.api.Application.UserCase.Card.RegisterCard;
import com.ByteCard.api.Application.UserCase.Card.UpdateCard;
import com.ByteCard.api.Application.UserCase.Client.FindClient;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardDTO;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardFindDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCard {
    private final RegisterCard registerClient;
    private final FindCard findCard;
    private final ActivesOrDelete activeOrDeleteClient;
    private final UpdateCard updeteClient;

    public ServiceCard(RegisterCard registerClient, FindCard findCard, ActivesOrDelete activeOrDeleteClient, UpdateCard updeteClient) {
        this.registerClient = registerClient;
        this.findCard = findCard;
        this.activeOrDeleteClient = activeOrDeleteClient;
        this.updeteClient = updeteClient;
    }

    public ResponseEntity<DataCard> register(Long id, DataCardDTO dto, UriComponentsBuilder builder) {
        var client = this.findClient(id);
        var card = this.registerClient.register(new Card(dto.limit(),client.getCpf(),client));
        var uri = builder.path("card/{îd}").buildAndExpand(card.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataCard(card));
    }

    private Client findClient(Long id) {
      var client =  this.findCard.findById(id);
        if(client.isPresent()){
            return client.get();
        }
        throw new RuntimeException("CLIENT NOT NULL");
    }

    public ResponseEntity<List<DataCard>> finds(DataCardFindDTO dto) {
       return ResponseEntity.ok( this.findCard.findAllByActivesTrue().stream().map(DataCard::new).collect(Collectors.toList()));
    }

    public ResponseEntity<Boolean> activesDelete(Long id){
        return ResponseEntity.ok(this.activeOrDeleteClient.activeOrDelete(id));
    }

    public ResponseEntity<DataCard> update(Long id, DataCardDTO dto) {
        var cliententity = this.updeteClient.update(id,new Card());
       return ResponseEntity.ok(new DataCard(cliententity));
    }
}
