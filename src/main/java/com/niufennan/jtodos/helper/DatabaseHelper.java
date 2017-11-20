package com.niufennan.jtodos.helper;

import java.sql.*;

public class DatabaseHelper {

    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(com.mysql.jdbc.Driver.class.getName());
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jtodos?serverTimezone=GMT%2b8","root","1234");
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void close(ResultSet rs, Statement statement, Connection connection){
        if(rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
