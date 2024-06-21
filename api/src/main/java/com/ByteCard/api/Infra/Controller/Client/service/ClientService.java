package com.ByteCard.api.Infra.Controller.Client.service;

import com.ByteCard.api.Application.UserCase.Client.ActiveOrDeleteClient;
import com.ByteCard.api.Application.UserCase.Client.FindClient;
import com.ByteCard.api.Application.UserCase.Client.RegisterClient;
import com.ByteCard.api.Application.UserCase.Client.UpdeteClient;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Domain.Entities.Client.model.DataUpdate;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClient;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClientDTO;
import com.ByteCard.api.Infra.Controller.Client.modal.DataClientFindDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final RegisterClient registerClient;
    private final FindClient findClient;
    private final ActiveOrDeleteClient activeOrDeleteClient;
    private final UpdeteClient updeteClient;

    public ClientService(RegisterClient registerClient, FindClient findClient, ActiveOrDeleteClient activeOrDeleteClient, UpdeteClient updeteClient) {
        this.registerClient = registerClient;
        this.findClient = findClient;
        this.activeOrDeleteClient = activeOrDeleteClient;
        this.updeteClient = updeteClient;
    }

    public ResponseEntity<DataClient> register(DataClientDTO dto, UriComponentsBuilder builder) {
        var cliententity = registerClient.register(new Client(dto.name(), dto.cpf(), dto.email(), dto.telephone()));
        var uri = builder.path("client/{id}").buildAndExpand(cliententity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataClient(cliententity.getId(), cliententity.getName(), cliententity.getCpf(), cliententity.getEmail(), cliententity.getTelephone(),cliententity.getActives()));
    }

    public ResponseEntity<List<DataClient>> finds(DataClientFindDTO dto) {
        if(dto.actives()){
            return ResponseEntity.ok(this.findClient.findAllByActivesTrue().stream().map(DataClient::new).collect(Collectors.toList()));
        }else {
            return ResponseEntity.ok(this.findClient.findAllByActivesFalse().stream().map(DataClient::new).collect(Collectors.toList()));
        }
    }

    public Boolean ativesOrdelete(Long id) {
        return this.activeOrDeleteClient.activeOrDelete(id);
    }

    public ResponseEntity<DataClient> update(Long id, DataClientDTO dto) {
        var cliententity = this.updeteClient.update(id,new DataUpdate(dto.name(), dto.cpf(), dto.email(), dto.telephone()));
        return ResponseEntity.ok(new DataClient(cliententity.getId(), cliententity.getName(), cliententity.getCpf(), cliententity.getEmail(), cliententity.getTelephone(),cliententity.getActives()));
    }

    public ResponseEntity<DataClient> find(DataClientFindDTO dto) {
        var cliententitya =this.findClient.findByCpf(dto.cpf());
        if(cliententitya.isPresent()) {
            var cliententity = cliententitya.get();
            return ResponseEntity.ok(new DataClient(cliententity.getId(), cliententity.getName(), cliententity.getCpf(), cliententity.getEmail(), cliententity.getTelephone(), cliententity.getActives()));
        }
        return null;
    }
}
