package com.ByteCard.api.Infra.Controller.Buy;


import com.ByteCard.api.Application.UserCase.Buy.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Buy.FindBuy;
import com.ByteCard.api.Application.UserCase.Buy.RegisterBuy;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuy;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuyDTO;
import com.ByteCard.api.Infra.Controller.Buy.modal.DataBuyFindDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("buy")
public class BuyController {
    private final RegisterBuy registerBuy;
    private final FindBuy findBuy;
    private final ActivesOrDelete activesOrDelete;

    public BuyController(RegisterBuy registerBuy, FindBuy findBuy, ActivesOrDelete activesOrDelete) {
        this.registerBuy = registerBuy;
        this.findBuy = findBuy;
        this.activesOrDelete = activesOrDelete;
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
