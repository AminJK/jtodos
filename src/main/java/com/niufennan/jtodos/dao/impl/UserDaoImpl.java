package com.niufennan.jtodos.dao.impl;

import com.niufennan.jtodos.dao.UserDao;
import com.niufennan.jtodos.helper.DatabaseHelper;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Inject
    private JdbcOperations template;

    public User getUserByName(String name){
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Todo> list=new ArrayList<Todo>();
        try{
            connection = DatabaseHelper.getConnection();
            statement= connection.prepareStatement("select * from users where name=?");
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));;
                return user;
            }
        }catch (SQLException ex){
            new RuntimeException(ex);
        }
        finally {
            DatabaseHelper.close(resultSet,statement,connection);
        }
        return null;
    }
    public User get(int id){
        Connection connection= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Todo> list=new ArrayList<Todo>();
        try{
            connection = DatabaseHelper.getConnection();
            statement= connection.prepareStatement("select * from users where id=?");
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            if (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));;
                return user;
            }
        }catch (SQLException ex){
            new RuntimeException(ex);
        }
        finally {
            DatabaseHelper.close(resultSet,statement,connection);
        }
        return null;
    }
    public int save(User user){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection = DatabaseHelper.getConnection();
            statement=connection.prepareStatement("INSERT INTO users(name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getName());
            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            //获取自增长Id
            if(resultSet.next()){
                return resultSet.getInt(1);
            }else{
                return -1;
            }
        }catch (SQLException ex){
            throw  new RuntimeException(ex);
        }finally {
            DatabaseHelper.close(null,statement,connection);
        }
    }
}
