package com.example.test;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin13")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"licheng","123456","2572175264@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admn","admin")==null) {
            System.out.println("用户名或密码错误，登陆失败！！");
        }else{
            System.out.println("查询成功");
        }
    }
}