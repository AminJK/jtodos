package com.niufennan.jtodos.servlet;

import com.niufennan.jtodos.dao.TodoDao;
import com.niufennan.jtodos.dao.UserDao;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.utils.UrlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@WebServlet("/todos/*")
public class TodoServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=UrlUtil.getUserName(request.getRequestURL().toString());
        System.out.println("TodoServlet");
        if("".equals(name)){
            //跳转至错误页面
            response.sendRedirect("error.jsp");

        }
        //实例化User数据库操作类
        UserDao userDao =new UserDao();
        User user=null;
        //获取用户
        user=userDao.getUserByName(name);
        if(null==user){
            //新用户
            user=new User();
            user.setName(name);
            user.setId(userDao.save(user));
        }
        //获取todo列表
        TodoDao todoDao=new TodoDao();
        List<Todo> list=todoDao.getTodoByUserId(user.getId());
        //将list和name存入request以备jsp页面使用
        request.setAttribute("todos",list);
        request.setAttribute("userid",user.getId());
        request.getRequestDispatcher("/todos.jsp").forward(request,response);
    }

    //post
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        int userid=Integer.parseInt(request.getParameter("user"));
        if(request.getParameter("todo")!=null){
            Todo todo=new Todo();
            todo.setCreateTime(new Date());
            todo.setItem(request.getParameter("todo"));
            todo.setUserId(userid);
            TodoDao todoDao=new TodoDao();
            todoDao.save(todo);
        }
        //获取user
        UserDao userDao=new UserDao();
        User user=userDao.get(userid);
        //页面跳转
        response.sendRedirect("/todos/"+user.getName());
    }

}
