package com.example.seveneleven.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seveneleven.model.MyUser;
import com.example.seveneleven.repository.MyUserRepository;

@Service
public class MyUserService {
    @Autowired
    private MyUserRepository userRepository;

    public void saveUser(MyUser user) {
        userRepository.save(user);
    }

    public List<MyUser> getAllUser() {
        return (List<MyUser>) userRepository.findAll();
    }

    public MyUser getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public void updateUser(MyUser user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public MyUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
