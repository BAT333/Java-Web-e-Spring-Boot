package com.ByteCard.api.Application.UserCase.User;

import com.ByteCard.api.Application.Gateways.RepositoryUser;
import com.ByteCard.api.Domain.Entities.User.User;

public class RegisterUser {
    public final RepositoryUser repositoryUser;

    public RegisterUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public User registerUser(User user){
        return this.repositoryUser.registerUser(user);
    }
}
