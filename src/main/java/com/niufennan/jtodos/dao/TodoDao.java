package com.niufennan.jtodos.dao;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.utils.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    public List<Todo> getAll(){
        Connection connection= null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Todo> list=new ArrayList<Todo>();
        try{
            connection =DatabaseHelper.getConnection();
            statement= connection.createStatement();
            resultSet=statement.executeQuery("select * from todos");
            while (resultSet.next()){
                Todo todo=new Todo();
                todo.setId(resultSet.getInt("id"));
                todo.setItem(resultSet.getString("item"));
                todo.setCreateTime(resultSet.getTimestamp("createtime"));
                todo.setUserId(resultSet.getInt("userid"));
                list.add(todo);
            }
        }catch (SQLException ex){
            new RuntimeException(ex);
        }
        finally {
            DatabaseHelper.close(resultSet,statement,connection);
        }
        return list;
    }

    public void save(Todo todo){
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            connection = DatabaseHelper.getConnection();
            statement=connection.prepareStatement("INSERT INTO todos (item,createtime,userid)VALUES(?,?,?);");
            statement.setString(1,todo.getItem());
            statement.setTimestamp(2, new  java.sql.Timestamp(todo.getCreateTime().getTime()));
            statement.setInt(3,todo.getUserId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw  new RuntimeException(ex);
        }finally {
            DatabaseHelper.close(null,statement,connection);
        }
    }
}
