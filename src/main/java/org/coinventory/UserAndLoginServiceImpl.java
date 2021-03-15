package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.coinventory.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndLoginServiceImpl implements UserAndLoginService {
    @Autowired
    UserService us;
    @Autowired
    LoginUserService lus;

    @Override
    public Users insertUserAndLogin(Users u, LoginUsers lu) {
        u = us.insertUser(u);
        lu.setUsers(u);
        lus.insertLoginUser(lu);
        return u;
    }

    @Override
    public Users updateLoginUser(Users u, LoginUsers lu) {
        return null;
    }
}
