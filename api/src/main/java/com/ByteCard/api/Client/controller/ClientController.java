package com.ByteCard.api.Client.controller;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Card.Repository.CardRepository;
import com.ByteCard.api.Client.Client;
import com.ByteCard.api.Client.Dados.DadosAlteraCliente;
import com.ByteCard.api.Client.Dados.DadosClient;
import com.ByteCard.api.Client.Dados.DadosListClient;
import com.ByteCard.api.Client.Repository.ClientRepository;
import com.ByteCard.api.Client.Services.ClienteService;
import com.ByteCard.api.Client.Services.ValidacaoClient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cliente")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private CardRepository repositoryCard;

    @GetMapping
    public List<DadosListClient> clientsTelaPrincipal(){
        return repository.findAllByAtivoTrue().stream().map(DadosListClient::new).sorted((Comparator.comparing(o -> o.name().toUpperCase()))).toList();

    }
    //FOCAR NAS MENSAGEM DE ERRO RETORNADAMAS
    @RequestMapping("cadastra")
    @PostMapping
    @Transactional
    public String cadastroClient(@RequestBody @Valid DadosClient dados){
        if(ValidacaoClient.validacao(dados)){
            if(repository.CLIENTE(dados.cpf()).isEmpty()){
                Client clients = repository.CLIENTE(dados.cpf()).orElse(new Client(dados));
                repository.save(clients);
                repositoryCard.save(ClienteService.creationCard(clients));
                return "Cliente cadastrado com sucesso";
            }else{
                throw new RuntimeException("Cliente JA CADASTRADO") ;
            }
        }else {
            throw new RuntimeException("CLIENTE NÃO CADASTRADO") ;
        }
    }
    @GetMapping("pesquisaClient/{cpf}")
    public Client pesquisaCliente(@PathVariable("cpf") String cpf){
        return  repository.CLIENT(cpf);

    }
    @DeleteMapping("/{cpf}")
    @Transactional
    public String excluirClient(@PathVariable("cpf") String cpf){
        Optional<Client> client = repository.CLIENTE(cpf);
        if(client.isEmpty()){
            throw new RuntimeException("CLINTE NÃO EXISTE") ;
        }else{
            client.ifPresent(Client::desativandoClient);
            List<Card> cards = repositoryCard.CARDS(client.get().getCpf());
            cards.forEach(Card::cancelar);
            return "CLIENTE EXCLUIDO COM SUCESSO";
        }
    }
    @PutMapping("/altera/{cpf}")
    @Transactional
    public String alteraCliente(@PathVariable("cpf")String cpf,@RequestBody DadosAlteraCliente cliente){
        Optional<Client> client =  this.repository.CLIENTE(cpf);
        if(client.isPresent()) {
            client.ifPresent(c-> c.alteraDados(cliente));
            List<Card> cards = this.repositoryCard.CARDS(cpf);
            cards.forEach(c->c.alteraDados(client.get().getCpf()));
            return "ALTERADO COM SUCESSO";
        }else {
            throw new RuntimeException("ERRO NA HORA DA ALTERAÇÃO");
        }
    }
}
