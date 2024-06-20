package com.ByteCard.api.Application.UserCase.User;


import com.ByteCard.api.Application.Gateways.RepositoryUser;
import com.ByteCard.api.Domain.Entities.User.User;

public class UpdeteUser {
    public final RepositoryUser repositoryUser;

    public UpdeteUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public User updateUser(Long id, User user){
       return this.repositoryUser.updeteUser(id,user);
    }
}
