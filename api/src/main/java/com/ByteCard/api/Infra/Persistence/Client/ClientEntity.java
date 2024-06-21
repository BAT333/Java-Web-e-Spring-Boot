package com.ByteCard.api.Infra.Persistence.Client;

import com.ByteCard.api.Infra.Persistence.Card.CardEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "email",length = 100)
    private String email;
    @Column(name = "telefone")
    private String telephone;
    @Column(name = "ativo")
    private Boolean actives;
    @Column
    @OneToMany(mappedBy = "clientID",cascade = CascadeType.ALL)
    private Set<CardEntity> cardEntities;

    public ClientEntity(Long id, String name, String cpf, String email, String telephone, Boolean actives) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telephone = telephone;
        this.actives = actives;
    }
}
