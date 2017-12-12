package com.niufennan.jtodos.dao;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.helper.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface TodoDao {
    public List<Todo> getAll();
    public List<Todo> getTodoByUserId(int userId);
    public void save(Todo todo);
}
