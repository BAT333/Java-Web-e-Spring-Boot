package com.ByteCard.api.Config.UserConfig;

import com.ByteCard.api.Application.Gateways.RepositoryUser;
import com.ByteCard.api.Application.UserCase.User.Logins;
import com.ByteCard.api.Application.UserCase.User.RegisterUser;
import com.ByteCard.api.Application.UserCase.User.UpdeteUser;
import com.ByteCard.api.Config.token.TokenService;
import com.ByteCard.api.Infra.Gatewars.User.RepositoryUserJPA;
import com.ByteCard.api.Infra.Gatewars.User.UserEntityMapper;
import com.ByteCard.api.Infra.Persistence.User.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UserConfig {
    @Bean
    public RepositoryUserJPA jpa (UserRepository repository, UserEntityMapper userEntityMapper, AuthenticationManager manager, TokenService service){
        return new RepositoryUserJPA(repository,userEntityMapper,manager,service);
    }

    @Bean
    public UserEntityMapper entityMapper(){
        return new UserEntityMapper();
    }
    @Bean
    public Logins logins(RepositoryUser jpa){
        return new Logins(jpa);
    }
    @Bean
    public RegisterUser user(RepositoryUser repositoryUser){
        return new RegisterUser(repositoryUser);
    }
    @Bean
    public UpdeteUser updeteUser (RepositoryUser repositoryUser){
        return new UpdeteUser(repositoryUser);
    }
}
