package com.ByteCard.api.Infra.Gatewars.User;

import com.ByteCard.api.Application.Gateways.RepositoryUser;
import com.ByteCard.api.Config.token.TokenService;
import com.ByteCard.api.Domain.Entities.User.User;
import com.ByteCard.api.Infra.Persistence.User.UserEntity;
import com.ByteCard.api.Infra.Persistence.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class RepositoryUserJPA implements RepositoryUser {
    private final UserRepository repository;
    private final UserEntityMapper userEntityMapper;

    private final AuthenticationManager manager;

    private final TokenService service;

    public RepositoryUserJPA(UserRepository repository, UserEntityMapper userEntityMapper, AuthenticationManager manager, TokenService service) {
        this.repository = repository;
        this.userEntityMapper = userEntityMapper;
        this.manager = manager;
        this.service = service;
    }

    @Override
    public User registerUser(User user) {
        if(!this.decoder(user.getLogins())) throw new BadCredentialsException("ERRO LOGIN OR PASSWORD");
        var password = this.encoder(user.getPasswords());
        var login = this.encoder(user.getLogins());
        var usersave = repository.save(new UserEntity(login,password,user.getRoles()));
        return this.userEntityMapper.toDomain(usersave);
    }
    private String encoder(String values) {
        return new BCryptPasswordEncoder().encode(values);
    }
    private Boolean decoder(String logins) {
        return this.repository.findAll().stream()
                .map(UserEntity::getLogins)
                .filter(userLogins ->new BCryptPasswordEncoder().matches(logins, userLogins))
                .toList().isEmpty();
    }

    @Override
    public User updeteUser(Long id,User user) {
        if(!this.decoder(user.getLogins())) throw new BadCredentialsException("ERRO LOGIN OR PASSWORD");
        var userUpdate = this.repository.findById(id);
        if(userUpdate.isPresent()){
            var usersEnti = this.userEntityMapper.toDomain(userUpdate.get());
            var password = this.encoder(user.getPasswords());
            var login = this.encoder(user.getLogins());
            usersEnti.update(new User(login,password,user.getRoles()));
            return this.userEntityMapper.toDomain( this.repository.save(this.userEntityMapper.toEntity(usersEnti)));
        }
        return null;
    }

    @Override
    public String login(String login,String passwords) {
        var decoder = this.decoder(login,passwords);
        if(decoder.isPresent()){
            var authenticationToken = new UsernamePasswordAuthenticationToken(decoder.get(),passwords);
            var user = manager.authenticate(authenticationToken);
            return service.geradorToken((UserEntity) user.getPrincipal());
        }
        return null;
    }
    private Optional<String> decoder(String login, String passwords) {
        var usersLogin = this.repository.findAll().stream()
                .filter(user->new BCryptPasswordEncoder().matches(login,user.getLogins()))
                .filter(users ->new BCryptPasswordEncoder().matches(passwords, users.getPassword()))
                .map(UserEntity::getLogins).toList();
        if(usersLogin.size()>1||usersLogin.isEmpty() )throw new BadCredentialsException("ERRO LOGIN OR PASSWORD");
        return Optional.ofNullable(usersLogin.getFirst());

    }
}
