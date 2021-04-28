package com.example.tfg2.user.tareas;

import com.example.tfg2.user.clases.User;
import com.example.tfg2.user.modelos.UserDB;

import java.util.concurrent.Callable;

public class TaskNewUser implements Callable<Boolean> {
    User user;

    public TaskNewUser(User user) {
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        return UserDB.createNewUser(user);
    }
}
