package com.venkat.service;

import com.venkat.model.User;
import com.venkat.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    


    @InjectMocks
    private UserService userService; //System under Test

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void saveUserTest(){
        User user = new User("venkat", "venkat", User.UserType.REGULAR_USER,true);
        user.setId(2L);

        when(userRepository.save(any(User.class))).thenReturn(user);

        User user2 = new User("venkat", "venkat", User.UserType.REGULAR_USER,true);
        Long id = userService.save(user2);
        assertEquals(2, id);
    }

    @Test
    public void saveUserAnotherWayTest(){
        when(userRepository.save(any(User.class))).then(invocation ->{
            User newUser = invocation.getArgument(0);
            newUser.setId(1L);
            return newUser;
        });

        User user2 = new User("venkat", "venkat", User.UserType.REGULAR_USER,true);
        Long id = userService.save(user2);
        assertEquals(1, id);
    }
}