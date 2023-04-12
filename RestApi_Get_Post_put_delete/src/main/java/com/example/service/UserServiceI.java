package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserServiceI {
   User addUser(User user);
   List<User> getAllUser();
   User findByEmail(String email);
   User updateUser(String email,User user);
   void  deleteUser(String email);
}
