package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.coinventory.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService us;
    @Autowired
    UserAndLoginService uls;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody Map<String,String> params) {
        Users u = new Users();
        u.setUname(params.get("uname"));
        u.setSurname(params.get("surname"));
        String email = params.getOrDefault("email", "");
        String role = params.getOrDefault("role", "");
        String password = params.getOrDefault("password", "");
        if (!email.isEmpty() && !role.isEmpty() && !password.isEmpty()) {
            LoginUsers lu = new LoginUsers();
            lu.setEmail(email);
            lu.setPwd(password);
            lu.setAdm(role.equals("administrator"));
            uls.insertUserAndLogin(u, lu);
        }
        else {
            us.insertUser(u);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
