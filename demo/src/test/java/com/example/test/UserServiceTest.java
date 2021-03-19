package com.example.test;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"lcheng","123456","licheng@qq.com"));
    }

    @Test
    public void login() {

        System.out.println(userService.Login(new User(null,"licheng","123456",null)));
    }

    @Test
    public void existsname() {
        System.out.println(userService.existsname("licheng"));
    }
}