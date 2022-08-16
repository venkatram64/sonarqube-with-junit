package com.venkat.controller;

import com.venkat.repository.LoginRepository;
import com.venkat.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController controller; //SUT(system under test)
    private LoginService service; //mock

    @BeforeEach
    public void setup(){
        this.service = mock(LoginService.class);
        this.controller = new LoginController(this.service);

    }

    @Test
    public void login() {
        //arrange
        when(service.login(anyString(),anyString())).thenReturn(true);
        //act
        String viewPath = controller.login("venkat", "venkat");
        //assert
        assertEquals("/home", viewPath);
    }
}