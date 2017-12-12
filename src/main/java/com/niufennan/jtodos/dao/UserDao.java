package com.niufennan.jtodos.dao;

import com.niufennan.jtodos.helper.DatabaseHelper;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    public User getUserByName(String name);
    public User get(int id);
    public int save(User user);
}
