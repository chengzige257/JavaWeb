package com.example.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTE-8");
        if(ServletFileUpload.isMultipartContent(req)){
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();//创建fileItemFactory的实现类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);//创建用于解析上传数据的工具类
            List<FileItem> list = null;
            try {
                list = servletFileUpload.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            for(FileItem fileItem: list){
                if(fileItem.isFormField()){//普通表单项

                }else{
                    try {
                        fileItem.write(new File("C:\\Users\\86153\\Desktop\\"+fileItem.getName()));
                        System.out.println("上传成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
