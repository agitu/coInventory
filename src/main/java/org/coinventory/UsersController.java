package org.coinventory;

import org.coinventory.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService us;

    @GetMapping
    List<Users> getUsers(@RequestParam(defaultValue = "") String surname, @RequestParam(defaultValue = "0") Integer page) {
        if (surname.isEmpty()) {
            return us.getUsersAll();
        }
        return us.getUsersBySurname(surname, PageRequest.of(page, 20));
    }
}
