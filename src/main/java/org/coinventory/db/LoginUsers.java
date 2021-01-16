package org.coinventory.db;

import javax.persistence.*;

@Entity
@Table(name = "login_users")
public class LoginUsers {
    @Id
    private Long id;
    private String email;
    private String password;
    private Boolean adm;
    @OneToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private Users users;

    public LoginUsers() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "LoginUsers{" +
                "id=" + id +
                '}';
    }
}
