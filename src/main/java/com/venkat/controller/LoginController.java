package com.venkat.controller;

import com.venkat.repository.LoginRepository;
import com.venkat.service.LoginService;

public class LoginController {

    private LoginService loginService;
    //shit+ctrl+T ==> to create the junit test

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    public String login(String userName, String password){
        //login
        //home
        if(loginService.login(userName, password)){
            return "/home";
        }else{
            return "/login";
        }
    }
}
