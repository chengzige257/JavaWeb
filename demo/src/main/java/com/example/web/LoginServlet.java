package com.example.web;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginuser = userService.Login(new User(null, username, password, null));
        if(loginuser==null){
            System.out.println("登陆失败");
            //失败
            req.getRequestDispatcher("index.html").forward(req,resp);
        }else{
            //成功
            System.out.println("登陆成功");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login_success.html");
            requestDispatcher.forward(req,resp);
        }
    }
}
