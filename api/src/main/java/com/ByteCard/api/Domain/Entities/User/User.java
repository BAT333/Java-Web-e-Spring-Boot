package com.ByteCard.api.Domain.Entities.User;
import java.util.Objects;

public class User {
    private Long id;
    private String logins;
    private String passwords;
    private UserRoles roles = UserRoles.USER;
    private Boolean actives = true;
    public User(){}
    public User(Long id ,String logins, String passwords, UserRoles roles, Boolean actives){
        if(logins == null ||passwords == null)throw new RuntimeException("NOT NULL");
        this.id =id;
        this.logins = logins;
        this.passwords = passwords;
        this.roles = roles;
        this.actives = actives;
    }
    public User(String logins, String passwords, UserRoles roles, Boolean actives){
        if(logins == null ||passwords == null)throw new RuntimeException("NOT NULL");
        this.logins = logins;
        this.passwords = passwords;
        this.roles = roles;
        this.actives = actives;
    }
    public User(String logins, String passwords){
        if(logins == null ||passwords == null)throw new RuntimeException("NOT NULL");
        this.logins = logins;
        this.passwords = passwords;
        this.actives = true;
    }


    public User(String logins, String password, UserRoles role) {
        if(logins == null ||password == null)throw new RuntimeException("NOT NULL");
        this.logins = logins;
        this.passwords = password;
        this.roles = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    public void setActives(Boolean actives) {
        this.actives = actives;
    }

    public Long getId() {
        return id;
    }

    public String getLogins() {
        return logins;
    }

    public String getPasswords() {
        return passwords;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public Boolean getActives() {
        return actives;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id != null && logins != null && id.equals(user.id) && logins.equals(user.logins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,logins);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", logins='" + logins + '\'' +
                ", passwords='" + passwords + '\'' +
                ", roles=" + roles +
                ", actives=" + actives +
                '}';
    }

    public User update(User user) {
        if(user.logins != null) {
            this.logins = user.getLogins();
        }
        if(user.passwords != null) {
            this.passwords = user.passwords;
        }
        if(user.roles != null) {
            this.roles = user.roles;
        }
        if(user.actives != null) {
            this.actives = user.actives;
        }
        return this;
    }
}
