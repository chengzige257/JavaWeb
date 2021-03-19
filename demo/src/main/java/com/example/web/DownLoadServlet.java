package com.example.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String downloadfilename="backiee-126643.jpg";//获取下载的文件名，文件不能放到WEB-INF文件下，因为浏览器获取不到
        ServletContext servletContext=getServletContext();
        String mimeType = servletContext.getMimeType("/file/" + downloadfilename);//获取要下载文件的类型
        resp.setContentType(mimeType);
        //Content-Disposition响应头表示数据怎么处理
        //attachment:表示已附件形式下载
        //filename表示下载的文件名
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("牛.jpg","UTF-8"));
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadfilename);
        OutputStream outputStream = resp.getOutputStream();
        IOUtils.copy(resourceAsStream,outputStream);//读取输入流的数据复制给输出流
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
