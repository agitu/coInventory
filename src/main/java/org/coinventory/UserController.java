package org.coinventory;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/adm")
    public Boolean getAdm(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(v -> v.getAuthority().equals("ROLE_ADMIN"));
    }
}
