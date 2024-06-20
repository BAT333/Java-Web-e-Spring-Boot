package com.ByteCard.api.Application.UserCase.User;


import com.ByteCard.api.Application.Gateways.RepositoryUser;

public class Logins {
    public final RepositoryUser repositoryUser;

    public Logins(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public String login(String login,String passwords){
        return this.repositoryUser.login(login, passwords);
    }

}
