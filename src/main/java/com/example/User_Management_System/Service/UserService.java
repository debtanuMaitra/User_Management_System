package com.example.User_Management_System.Service;

import com.example.User_Management_System.Entity.User;
import com.example.User_Management_System.Exception.UserNotFoundException;
import com.example.User_Management_System.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String createUser(User user) {
        userRepository.save(user);
        return "User Added Successfully";
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User Not Found"));
    }

    public User updateUser(Integer id, User updatedUser) throws UserNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User Not Found"));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public String deleteUser(Integer id) throws UserNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User Not Found"));
        userRepository.delete(user);
        return "User Deleted Successfully";
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
