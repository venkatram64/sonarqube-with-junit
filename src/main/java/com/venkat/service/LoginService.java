package com.venkat.service;

import com.venkat.model.User;
import com.venkat.repository.LoginRepository;

public class LoginService {

    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public boolean login(String userName, String password){
        User user =  loginRepository.findByUser(userName);

        if(user.getPassword().equalsIgnoreCase(password)){
            return true;
        }else{
            return false;
        }
    }
}
