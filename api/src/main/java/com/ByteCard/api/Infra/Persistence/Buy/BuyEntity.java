package com.ByteCard.api.Infra.Persistence.Buy;

import com.ByteCard.api.Domain.CategoryBuy;
import com.ByteCard.api.Domain.Entities.Card.Card;
import com.ByteCard.api.Domain.Entities.Client.Client;
import com.ByteCard.api.Infra.Persistence.Card.CardEntity;
import com.ByteCard.api.Infra.Persistence.Client.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "compra")
public class BuyEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private BigDecimal value;
    @Column(name = "data_compra")
    private LocalDateTime date;
    @Column(name = "estabelecimento")
    private String institute;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoryBuy categoryBuy;
    @Column(name = "Numero_cartao")
    private String numberCard;
    @ManyToOne
    @JoinColumn(name = "Client_id")
    private ClientEntity clientId;
    @ManyToOne
    @JoinColumn(name = "Cartao_id")
    private CardEntity cardId;
    @Column(name = "ativo")
    private Boolean actives = true;


}
