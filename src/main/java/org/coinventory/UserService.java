package org.coinventory;

import org.coinventory.db.Users;

public interface UserService {
    Users insertUser(Users u);
    Users updateUser(Users u);
    void deleteUser(long id);
    Users getUser(long id);
}
