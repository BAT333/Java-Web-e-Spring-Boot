package com.ByteCard.api.Infra.Controller.Card;

import com.ByteCard.api.Application.UserCase.Card.ActivesOrDelete;
import com.ByteCard.api.Application.UserCase.Card.FindCard;
import com.ByteCard.api.Application.UserCase.Card.RegisterCard;
import com.ByteCard.api.Application.UserCase.Card.UpdateCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCard;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardDTO;
import com.ByteCard.api.Infra.Controller.Card.modal.DataCardFindDTO;
import com.ByteCard.api.Infra.Controller.Card.service.ServiceCard;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private ServiceCard serviceCard;
    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<DataCard> register(Long id,@RequestBody @Valid DataCardDTO dto, UriComponentsBuilder builder){

        return serviceCard.register(id,dto,builder);
    }
    @GetMapping
    public ResponseEntity<List<DataCard>> finds(@RequestBody DataCardFindDTO dto){

        return this.serviceCard.finds(dto);
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Boolean> activesOrDelete(@PathVariable Long id){

        return this.serviceCard.activesDelete(id);
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataCard> update(@PathVariable Long id,@RequestBody DataCardDTO dto){
        return this.serviceCard.update(id,dto);
    }
}
