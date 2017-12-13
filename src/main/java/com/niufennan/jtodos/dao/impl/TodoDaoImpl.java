package com.niufennan.jtodos.dao.impl;

import com.niufennan.jtodos.dao.TodoDao;
import com.niufennan.jtodos.helper.DatabaseHelper;
import com.niufennan.jtodos.models.Todo;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao{

    @Inject
    private JdbcOperations template;
    public List<Todo> getAll(){
        List<Todo> list=template.query("select * from todos", new TodoRowMapper());
        return list;
    }
    public List<Todo> getTodoByUserId(int userId){
        List<Todo> list=template.query("select * from todos where userId=?", new Object[]{userId},new TodoRowMapper());
        return list;
    }
    public void save(Todo todo){
        String sql="INSERT INTO todos (item,createtime,userid)VALUES(?,?,?);";
        template.update(sql,new Object[]{todo.getItem(),todo.getCreateTime(),todo.getUserId()});
    }

    private class TodoRowMapper implements RowMapper<Todo>{
        @Nullable
        public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
            Todo todo=new Todo();
            todo.setId(resultSet.getInt("id"));
            todo.setItem(resultSet.getString("item"));
            todo.setCreateTime(resultSet.getTimestamp("createtime"));
            todo.setUserId(resultSet.getInt("userid"));
            System.out.println(i);
            return todo ;
        }
    }
}
