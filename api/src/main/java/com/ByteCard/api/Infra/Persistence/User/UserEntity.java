package com.ByteCard.api.Infra.Persistence.User;

import com.ByteCard.api.Domain.Entities.User.UserRoles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String logins;
    @Column(name = "password")
    private String passwords;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoles roles = UserRoles.USER;
    @Column(name = "actives")
    private Boolean actives = true;

    public UserEntity(String login, String password, UserRoles roles) {
        this.logins = login;
        this.passwords = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles == UserRoles.ADMIN)return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("R0LE_USER"));
        return List.of(new SimpleGrantedAuthority("R0LE_USER"));
    }

    @Override
    public String getPassword() {
        return this.getPasswords();
    }

    @Override
    public String getUsername() {
        return this.getLogins();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
