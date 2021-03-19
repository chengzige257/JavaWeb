package com.example.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource source;
    static {

        try {
            Properties pros = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            //System.out.println(is);
//            pros.load(new FileInputStream("druid.properties"));
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }
//    //************************************************
//    public static Connection getConnection() throws Exception {
//        // 1.读取配置文件中的4个基本信息
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
//        Properties pros = new Properties();
//        pros.load(is);
//
//        String user = pros.getProperty("user");
//        String password = pros.getProperty("password");
//        String url = pros.getProperty("url");
//        String driverclass = pros.getProperty("driverclass");
//
//        // 2.加载驱动
//        Class.forName(driverclass);
//        // 3.获取连接
//        Connection conn = DriverManager.getConnection(url, user, password);
//        return conn;
//    }
    //关闭连接和Statement的操作
    public static void closeResourse(Connection conn, Statement ps) {
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
