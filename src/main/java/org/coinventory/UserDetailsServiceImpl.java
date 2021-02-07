package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.coinventory.db.LoginUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LoginUsersRepository loginUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<LoginUsers> loginUsers = loginUsersRepository.findByEmail(username);
        if (loginUsers.isEmpty()) {
            throw new UsernameNotFoundException(String.format("%s not found", username));
        }
        else {
            return new UserDetailsImpl(loginUsers.get());
        }
    }
}
