package com.example.tfg2.user.clases;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String nameUser;
    private String email;
    private String password;

    public User(int id, String nameUser, String email, String password) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
    }

    public User(String nameUser, String email, String password) {
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(nameUser, user.nameUser) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameUser, email);
    }
}
