package com.example.dao;

import com.example.pojo.User;

public interface UserDao {

    public User queryUserByUsername(String username);//根据用户名查询用户信息,返回null说明用户不存在
    public int saveUser(User user);//保存用户信息
    public User queryUserByUsernameAndPassword(String username,String password);//返回null，说明用户名或密码错误

}
