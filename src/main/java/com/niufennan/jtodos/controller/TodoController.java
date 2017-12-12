package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.dao.TodoDao;
import com.niufennan.jtodos.dao.UserDao;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.service.TodoService;
import com.niufennan.jtodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;
    @RequestMapping(value ="/todos/{name}" ,method = RequestMethod.GET)
    public String home(@PathVariable String name, HttpServletRequest request){
        User user=userService.getUserByName(name);
        //获取todo列表
        List<Todo> list= todoService.getTodoByUserId(user.getId());
        //将list和name存入request以备jsp页面使用
        request.setAttribute("todos",list);
        request.setAttribute("userid",user.getId());
        return "todos";
    }
    @RequestMapping(value ="/todos" ,method = RequestMethod.POST)
    public String home(HttpServletResponse response, Todo todo) throws IOException {
        todoService.save(todo);
        //获取user
        User user = userService.get(todo.getUserId());
        //页面跳转
        return "redirect:/todos/"+user.getName();
    }
}
