package org.coinventory.db;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uname;
    private String surname;
    @OneToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private LoginUsers loginUsers;

    public Users() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LoginUsers getLoginUsers() {
        return loginUsers;
    }

    public void setLoginUsers(LoginUsers loginUsers) {
        this.loginUsers = loginUsers;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                '}';
    }
}
