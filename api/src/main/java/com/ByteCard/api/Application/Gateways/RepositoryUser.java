package com.ByteCard.api.Application.Gateways;


import com.ByteCard.api.Domain.Entities.User.User;

public interface RepositoryUser {
    User registerUser(User user);
    User updeteUser(Long id, User user);
    String login(String login,String passwords);

}
