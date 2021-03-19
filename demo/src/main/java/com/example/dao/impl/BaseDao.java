package com.example.dao.impl;

import com.example.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    public int update(String sql,Object ...args)  {
        Connection conn = null;

        try {
            conn=JdbcUtils.getConnection();
            return queryRunner.update(conn,sql,args);
        }  catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.closeResourse(conn,null);
        }
        return -1;
    }

    public <T> T queryForOne(Class<T> type,String sql,Object ...args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResourse(conn,null);
        }
        return null;
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResourse(conn,null);
        }
        return null;
    }
    public Object queryForSingleValue(String sql,Object ...args) {
        Connection conn = null;
        try {
            conn=JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new ScalarHandler(),args);//ScalarHandler()将单个值封装
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.closeResourse(conn,null);
        }
        return null;
    }
}
