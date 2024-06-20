package com.ByteCard.api.Infra.Controller.Card;

import com.ByteCard.api.Application.UserCase.Card.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Card.FindCard;
import com.ByteCard.api.Application.UserCase.Card.RegisterCard;
import com.ByteCard.api.Application.UserCase.Card.UpdateCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardDTO;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardFindDTO;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClient;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    private final RegisterCard registerClient;
    private final FindCard findClient;
    private final ActivesOrDelete activeOrDeleteClient;
    private final UpdateCard updeteClient;

    public CardController(RegisterCard registerClient, FindCard findClient, ActivesOrDelete activeOrDeleteClient, UpdateCard updeteClient) {
        this.registerClient = registerClient;
        this.findClient = findClient;
        this.activeOrDeleteClient = activeOrDeleteClient;
        this.updeteClient = updeteClient;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<DataCard> register(@RequestBody DataCardDTO dto){
        return null;
    }
    @GetMapping
    public ResponseEntity<DataCard> finds(@RequestBody DataCardFindDTO dto){
        return null;
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity<Boolean> activesOrDelete(){
        return null;
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DataCard> update(@RequestBody DataCardDTO dto){
        return null;
    }
}
