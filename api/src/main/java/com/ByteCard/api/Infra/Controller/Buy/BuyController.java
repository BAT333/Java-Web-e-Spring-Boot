package com.ByteCard.api.Infra.Controller.Buy;


import com.ByteCard.api.Application.UserCase.Buy.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Buy.FindBuy;
import com.ByteCard.api.Application.UserCase.Buy.RegisterBuy;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuy;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuyDTO;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuyFindDTO;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardDTO;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardFindDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("buy")
public class BuyController {
    private final RegisterBuy registerClient;
    private final FindBuy findClient;
    private final ActivesOrDelete activeOrDeleteClient;

    public BuyController(RegisterBuy registerClient, FindBuy findClient, ActivesOrDelete activeOrDeleteClient) {
        this.registerClient = registerClient;
        this.findClient = findClient;
        this.activeOrDeleteClient = activeOrDeleteClient;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<DataBuy> register(@RequestBody DataBuyDTO dto){
        return null;
    }
    @GetMapping
    public ResponseEntity<DataBuy> finds(@RequestBody DataBuyFindDTO dto){
        return null;
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity<Boolean> activesOrDelete(){
        return null;
    }

}
