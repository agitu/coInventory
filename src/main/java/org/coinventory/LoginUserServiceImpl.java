package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.coinventory.db.LoginUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    LoginUsersRepository lur;

    @Override
    public LoginUsers insertLoginUser(LoginUsers u) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        u.setPwd(encoder.encode(u.getPwd()));
        return lur.save(u);
    }

    @Override
    public LoginUsers updateLoginUser(LoginUsers u) {
        return null;
    }

    @Override
    public void deleteLoginUser(long id) {

    }

    @Override
    public LoginUsers getLoginUser(long id) {
        return null;
    }
}
