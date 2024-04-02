package com.ByteCard.api.Compra.Service;

import com.ByteCard.api.Card.Card;
import com.ByteCard.api.Client.Client;
import com.ByteCard.api.Compra.Compra;
import com.ByteCard.api.Compra.Dados.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompraService {

    public Compra realizarCompra(Card card, Client client, DadosCompra compra, BigDecimal decimal){
        this.verficacaoData(card);
        Compra compra1 = new Compra(compra,client,card);
        if(card.isStatus()&&compra.estabelecimento().length()>=5&&this.verficacaoLimite(compra,card,decimal)>=0){
            return compra1;
        }else{
            throw new RuntimeException(  "COMPRA NÃO REALIZADA");
        }
    }

    private void verficacaoData(Card card) {
        int comparando =  LocalDate.now().compareTo(card.getDate());
        if(comparando>=0){
            card.cancelar();
            throw new RuntimeException( "CARTÃO CANCELADO DATA DE VALIDADE VENCIDA");
        }
    }

    private int verficacaoLimite(DadosCompra compra, Card card,BigDecimal decimal) {
        int compravalor;
        if(decimal!=null){
            compravalor = card.getLimit().compareTo(compra.valor().add(decimal));
        }else{
            compravalor = card.getLimit().compareTo(compra.valor());
        }
        return compravalor;
    }


    public FaturaMes faturaCliente( List<Compra> dadosCompra) {
        List<FaturaDados> dados = dadosCompra.stream().map(FaturaDados::new).toList();
        BigDecimal valorTotal =  dadosCompra.stream().map(Compra::getValor).reduce(BigDecimal.ZERO,BigDecimal::add);
        return new FaturaMes(dados,valorTotal);
    }
    public static String formatacaoData(LocalDate date){
        int comparando =  LocalDate.now().compareTo(date);
        if(comparando >=0){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            return date.format(formatter);
        }else{
            throw new RuntimeException( "DATA SELECIONADA INVALIDA");
        }
    }

    public DadosRelatorio relatorioCategoria(List<DadosCategoriaRelatorio> compras) {
        BigDecimal  valorTotal =compras.stream().map(DadosCategoriaRelatorio::getTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
        return new DadosRelatorio(compras,valorTotal);
    }
}
