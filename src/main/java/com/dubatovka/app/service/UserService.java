package com.dubatovka.app.service;

import com.dubatovka.app.entity.User;

import java.util.List;

public interface UserService {
    
    List<User> getAllUsers();
    
    List<User> getUsersByFirstAndLastName(String fName, String lName);
}
