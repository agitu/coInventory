package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.coinventory.db.Users;

public interface UserAndLoginService {
    Users insertUserAndLogin(Users u, LoginUsers lu);
    Users updateLoginUser(Users u, LoginUsers lu);
}
