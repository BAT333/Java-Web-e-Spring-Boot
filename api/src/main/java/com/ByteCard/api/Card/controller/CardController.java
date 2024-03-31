package com.ByteCard.api.Card.controller;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Card.Dados.*;
import com.ByteCard.api.Card.Repository.CardRepository;
import com.ByteCard.api.Card.Services.CardService;
import com.ByteCard.api.Card.Services.Cardrandom;
import com.ByteCard.api.Card.Services.Validacao;
import com.ByteCard.api.Client.Client;
import com.ByteCard.api.Client.Repository.ClientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController

@CrossOrigin("*")
@RequestMapping("cartao")
public class CardController {
    @Autowired
    private CardRepository repository;
    @Autowired
    private ClientRepository repositoryClient;
    @GetMapping
    public Page<DadosListCard> cardList(@PageableDefault(size = 10,sort = {"numberCard"}) Pageable pageable){
        return repository.findAll(pageable).map(DadosListCard::new);
        //size=1&page=3;
    }
    @GetMapping("cardAtivo")
    public Page<DadosListCard> cardListAtivo(@PageableDefault(size = 10,sort = {"numberCard"}) Pageable pageable){
        return repository.findAllBystatusTrue(pageable).map(DadosListCard::new);
        //size=1&page=3;
    }

    @PostMapping
    @Transactional
    @RequestMapping("cadastro")
    public String card(@RequestBody @Valid DadosCadastroCard card){
        if (Validacao.validacao(card.cpf())) {
            Optional<Client> clientes =  repositoryClient.CLIENTE(card.cpf()).filter(Client::getAtivo);
            if(clientes.isPresent()) {
                repository.save( CardService.createCard(clientes.get(),card));
                return "CARTÃO CADASTRADO";
            }else{
                throw new RuntimeException ("CLIENTE NÃO EXISTE");
            }
        }else {
            throw new RuntimeException ("CARTÃO NÃO CADASTRADO");

        }
    }
    @GetMapping
    @RequestMapping("desativado")
    public Page<DadosListCard> cardListDesativado(@PageableDefault(size = 10,sort = {"numberCard"}) Pageable pageable){
        return repository.findAllBystatusFalse(pageable).map(DadosListCard::new);
        //size=1&page=3;
    }
    @GetMapping
    @RequestMapping("pesquisa")
    public DadosListCard cardpesquisa(@RequestBody DadosPesquisaCard card){
        List<DadosListCard> cards = repository.findAll().stream().map(DadosListCard::new).toList();
        return cards.stream().filter(c -> c.numberCard().equals(card.numberCard()) && c.cpf().equals(card.cpf())).collect(Collectors.toList()).getFirst();
    }

    @PutMapping
    @Transactional
    @RequestMapping("/{number:[0-9]+}")
    public String ativarCard(@PathVariable("number") long numberCard){
        Optional<Card> card =repository.CARDExis(numberCard);
        if(card.isPresent()&&card.get().getClientID().getAtivo()){
            card.ifPresent(Card::ativarCancelar);
            return "ATIVO";
        }else {
            throw new RuntimeException ("CARTÃO NÃO ENCONTRADO");
        }
    }
    @PutMapping
    @Transactional
    @RequestMapping("/alteraLimite/{number:[0-9]+}")
    public dadosAtualizarCard alteraLimite(@PathVariable("number") long numbercard, @RequestBody @Valid Altera altera){
        Optional<Card> card =repository.CARDExis(numbercard);
        if(card.isPresent()&&card.get().getClientID().getAtivo()) {
            card.ifPresent(c-> c.atualizarLimite(altera));
            return new dadosAtualizarCard(repository.CARD(numbercard));
        }else{
            throw new RuntimeException ("CARTÃO NÃO ENCONTRADO");
        }

    }

    @DeleteMapping("/{d}")
    @Transactional
    public void excluir(@PathVariable long id){
        repository.deleteById(id);
    }
    @DeleteMapping("/desativaCard/{number:[0-9]+}")
    @Transactional
    public String cancelar(@PathVariable("number") long numbercard){
        Optional<Card> card =repository.CARDExis(numbercard);
        if(card.isPresent()){
            card.ifPresent(Card::cancelar);
            return "cancelado";
        }else{
            throw new RuntimeException ("ERRO");
        }
    }
}
