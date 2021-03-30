package org.coinventory;

import org.coinventory.db.Users;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Users insertUser(Users u);
    Users updateUser(Users u);
    void deleteUser(long id);
    Users getUser(long id);
    List<Users> getUsersBySurname(String surname, Pageable p);
    List<Users> getUsersAll();
}
