package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.dao.TodoDao;
import com.niufennan.jtodos.dao.UserDao;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class TodoController {
    @RequestMapping(value ="/todos/{name}" ,method = RequestMethod.GET)
    public String home(@PathVariable String name, HttpServletRequest request){
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
        return "todos";
    }
    @RequestMapping(value ="/todos" ,method = RequestMethod.POST)
    public String home(HttpServletResponse response, Todo todo) throws IOException {
        TodoDao todoDao=new TodoDao();
        todoDao.save(todo);
        //获取user
        UserDao userDao=new UserDao();
        User user=userDao.get(todo.getUserId());
        //页面跳转
        return "redirect:/todos/"+user.getName();
    }
}
