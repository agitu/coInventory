package org.coinventory;

import org.coinventory.db.LoginUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(LoginUsers loginUsers) {
        this.username = loginUsers.getEmail();
        this.password = loginUsers.getPwd();
        if (loginUsers.getAdm()) {
            this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
                                            new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public UserDetailsImpl() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
