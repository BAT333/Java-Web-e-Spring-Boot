package com.ByteCard.api.Infra.Gatewars.User;

import com.ByteCard.api.Domain.Entities.User.User;
import com.ByteCard.api.Infra.Gatewars.EntityFactory;
import com.ByteCard.api.Infra.Persistence.User.UserEntity;

public class UserEntityMapper implements EntityFactory<User, UserEntity> {
    @Override
    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(),userEntity.getLogins(), userEntity.getPasswords(),userEntity.getRoles(),userEntity.getActives());
    }

    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getLogins(), user.getPasswords(),user.getRoles(),user.getActives());
    }
}
