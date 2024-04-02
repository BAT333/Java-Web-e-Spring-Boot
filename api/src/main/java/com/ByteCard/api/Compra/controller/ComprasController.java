package com.ByteCard.api.Compra.controller;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Card.Repository.CardRepository;
import com.ByteCard.api.Client.Client;
import com.ByteCard.api.Client.Repository.ClientRepository;
import com.ByteCard.api.Compra.Compra;
import com.ByteCard.api.Compra.Dados.*;
import com.ByteCard.api.Compra.Repository.CompraRepository;
import com.ByteCard.api.Compra.Service.CompraService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("compras")
public class ComprasController {
    @Autowired
    private CompraRepository repository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;
    @PostMapping
    @Transactional
    public String compras(@RequestBody @Valid DadosCompra compra){
        if(cardRepository.CARDExis(compra.numberCard()).isPresent()){
            Card card = cardRepository.CARD(compra.numberCard());
            Client client = clientRepository.getReferenceById(card.getClientID().getId());
            repository.save(new CompraService().realizarCompra(card,client,compra,repository.getValores(compra.numberCard())));
            return "COMPRA REALIZADA";
        }else{
            throw new RuntimeException("CARTÃO NÃO EXISTE");
        }
    }
    @GetMapping
    @RequestMapping("fatura")
    public FaturaMes fatura(@RequestBody  Dadoslist dadosfatura){
        List<Compra> dadosCompra = repository.COMPRASLIST(dadosfatura.numberCard(),CompraService.formatacaoData(dadosfatura.date()));
        return new CompraService().faturaCliente(dadosCompra);
    }

    @GetMapping
    @RequestMapping("/categoria")
    public DadosRelatorio relatorioCategoria(@RequestBody DadosPesquiRelatorio dados){
        List<DadosCategoriaRelatorio> compras = repository.COMPRAS(CompraService.formatacaoData(dados.date()), dados.numberCard());
        return new CompraService().relatorioCategoria(compras);
    }
    @GetMapping
    @RequestMapping("relatorio")
    public RelatorioCliente relatorio(@RequestBody DadosPesquiRelatorio dados){
        return new RelatorioCliente(repository.COMPRA_MAIOR_VALORS(CompraService.formatacaoData(dados.date())),repository.ComproNada(CompraService.formatacaoData(dados.date())),repository.RELATOIRO_DE_COMPRAS(CompraService.formatacaoData(dados.date())));
    }

}
