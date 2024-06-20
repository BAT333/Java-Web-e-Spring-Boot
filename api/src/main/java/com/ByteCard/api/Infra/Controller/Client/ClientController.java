package com.ByteCard.api.Infra.Controller.Client;

import com.ByteCard.api.Application.UserCase.Client.ActiveOrDeleteClient;
import com.ByteCard.api.Application.UserCase.Client.FindClient;
import com.ByteCard.api.Application.UserCase.Client.RegisterClient;
import com.ByteCard.api.Application.UserCase.Client.UpdeteClient;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClient;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClientDTO;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClientFindDTO;
import com.ByteCard.api.Infra.Controller.Client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DataClient> register(@RequestBody DataClientDTO dto, UriComponentsBuilder builder){
        return this.service.register(dto,builder);
    }
    @GetMapping
    public ResponseEntity<DataClient> find(@RequestBody DataClientFindDTO dto){
       return this.service.find(dto);
    }
    @GetMapping("/list")
    public ResponseEntity<List<DataClient>> finds(@RequestBody DataClientFindDTO dto){
        return this.service.finds(dto);
    }
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Boolean> activesOrDelete(@PathVariable Long id){
        return ResponseEntity.ok(this.service.ativesOrdelete(id));
    }
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DataClient> update(@PathVariable Long id,@RequestBody DataClientDTO dto){
        return this.service.update(id,dto);
    }
}
