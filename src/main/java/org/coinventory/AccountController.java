package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    LoginUserService lus;

    @GetMapping("/{id}")
    public LoginUsers getLoginUser(@PathVariable Long id) {
        return lus.getLoginUser(id);
    }
}
