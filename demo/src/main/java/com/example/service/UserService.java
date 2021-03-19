package com.example.service;

import com.example.pojo.User;

public interface UserService {
    public void registUser(User user);
    public User Login(User user);
    public boolean existsname(String username);
}
