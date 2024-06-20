package com.ByteCard.api.Infra.Persistence.Card;

import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Persistence.Client.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CardEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_cartao",unique = true)
    private String numberCard;
    @Column(name = "validade")
    private LocalDate date;
    @Column(name = "cvv" )
    private String cvv;
    @Column(name = "limite")
    private BigDecimal limit;
    @Column(name ="ativo" )
    private Boolean actives = true;
    @Column(name ="cpf_client" )
    private String clientCpf;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClientEntity clientID;
}
