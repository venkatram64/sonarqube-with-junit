package com.venkat.repository;

import com.venkat.model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {

    private Map<String, User> users = new HashMap<>();

    public LoginRepository(){
        users.put("venkat", User.createAdminUser("venkat", "venkat"));
        users.put("srijan", User.createRegularUser("srijan", "srijan"));
        users.put("ram", User.createRegularUser("ram", "ram"));
    }

    public User findByUser(String userName){
        return users.get(userName);
    }
}
