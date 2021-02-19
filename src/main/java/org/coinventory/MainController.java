package org.coinventory;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/adm")
    @ResponseBody
    public Boolean getAdm(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(v -> v.getAuthority().equals("ROLE_ADMIN"));
    }
}
