package com.example.User_Management_System;

import com.example.User_Management_System.Entity.User;
import com.example.User_Management_System.Exception.UserNotFoundException;
import com.example.User_Management_System.Repository.UserRepository;
import com.example.User_Management_System.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void createUserTest() {
        User user = buildMockUser();
        userService.createUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getUserByIdTest() throws UserNotFoundException {
        User user = buildMockUser();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserById(1));
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void updateUserTest() throws UserNotFoundException {
        User user = buildMockUser();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        user.setEmail("updatedTest@gmail.com");
        when(userRepository.save(any())).thenReturn(user);
        User updatedUser = userService.updateUser(1, user);
        verify(userRepository, times(1)).save(updatedUser);
        assertEquals("updatedTest@gmail.com", updatedUser.getEmail());
    }

    @Test
    public void deleteUserTest() throws UserNotFoundException {
        User user = buildMockUser();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        userService.deleteUser(1);
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).delete(user);
    }

    private User buildMockUser() {
        User user = new User();
        user.setEmail("Test@gmail.com");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setId(1);
        return user;
    }

}

