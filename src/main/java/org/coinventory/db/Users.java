package org.coinventory.db;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    private Long id;
    private String uname;
    private String surname;

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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                '}';
    }
}
