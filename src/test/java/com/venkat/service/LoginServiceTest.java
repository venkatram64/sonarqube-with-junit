package com.venkat.service;

import com.venkat.model.User;
import com.venkat.repository.LoginRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private LoginService loginService;  //SUT(System under test)
    private LoginRepository loginRepository; //mock
    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        loginRepository = mock(LoginRepository.class);
        loginService = new LoginService(loginRepository);
        user = new User("venkat", "venkat", User.UserType.REGULAR_USER,true);
    }

    @Test
    public void login() {
        //arrange
        when(loginRepository.findByUser(anyString())).thenReturn(user);
        //act
        boolean status = loginService.login("venkat", "venkat");
        //assert
        assertTrue(status);
    }

    @Test
    @Disabled
    public void loginWithOutService() {
        //arrange
        when(loginRepository.findByUser(anyString())).thenThrow(new IllegalArgumentException());
        //act
        boolean status = loginService.login("test", "test");
        //assert
        assertTrue(status);
    }
}