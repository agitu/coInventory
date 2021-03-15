package org.coinventory;

import org.coinventory.db.LoginUsers;

public interface LoginUserService {
    LoginUsers insertLoginUser(LoginUsers u);
    LoginUsers updateLoginUser(LoginUsers u);
    void deleteLoginUser(long id);
    LoginUsers getLoginUser(long id);
}
