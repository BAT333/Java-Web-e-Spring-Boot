package com.ByteCard.api.Infra.Controller.User;

import com.ByteCard.api.Application.UserCase.Card.UpdateCard;
import com.ByteCard.api.Application.UserCase.User.Logins;
import com.ByteCard.api.Application.UserCase.User.RegisterUser;
import com.ByteCard.api.Application.UserCase.User.UpdeteUser;
import com.ByteCard.api.Domain.Entities.User.User;
import com.ByteCard.api.Infra.Controller.User.model.DataLogins;
import com.ByteCard.api.Infra.Controller.User.model.DataLoginsRegister;
import com.ByteCard.api.Infra.Controller.User.model.DataLoginsUpdate;
import com.ByteCard.api.Infra.Controller.User.model.DataToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final Logins logins;
    private final RegisterUser registerUser;
    private final UpdeteUser updeteUser;

    public UserController(Logins logins, RegisterUser registerUser, UpdeteUser updeteUser) {
        this.logins = logins;
        this.registerUser = registerUser;
        this.updeteUser = updeteUser;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<DataToken> login(@RequestBody @Valid DataLogins dataLogins){
        return ResponseEntity.ok(new DataToken(logins.login(dataLogins.logins(), dataLogins.password())));
    }
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DataLogins> register(@RequestBody @Valid DataLoginsRegister register){
        var logi = this.registerUser.registerUser(new User(register.logins(),register.password(),register.role()));
        return ResponseEntity.ok(new DataLogins(logi.getId(),logi.getLogins(),logi.getPasswords()));
    }
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<DataLoginsUpdate> update(@RequestBody @Valid DataLoginsRegister register,@PathVariable Long id){
        var logi = this.updeteUser.updateUser(id,new User(register.logins(),register.password(),register.role()));
        return ResponseEntity.ok(new DataLoginsUpdate(logi.getLogins(),logi.getPasswords()));
    }
}
