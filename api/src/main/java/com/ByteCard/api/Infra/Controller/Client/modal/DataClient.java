package com.ByteCard.api.Infra.Controller.Client.modal;

import com.ByteCard.api.Domain.Entities.Client.Client;
import jakarta.validation.constraints.NotNull;

public record DataClient(
        Long id,
        @NotNull
        String name,
        @NotNull

        String cpf,
        @NotNull

        String email,
        @NotNull

        String telephone,
        Boolean actives
) {
        public DataClient(Client client){
                this(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getTelephone(), client.getActives());
        }
}
