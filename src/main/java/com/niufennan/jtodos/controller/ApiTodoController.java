package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.base.BaseController;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.models.UserMessage;
import com.niufennan.jtodos.service.TodoService;
import com.niufennan.jtodos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class ApiTodoController extends BaseController{

    @Autowired
    TodoService todoService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/api/index",method = RequestMethod.POST)
    public Object apiIndex(HttpServletRequest request,@RequestBody Map map){
        //获取首页数据
        String userId=request.getAttribute("tokenId").toString();
        Integer month=Integer.parseInt( map.get("month").toString());
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for (int i=0;i<1;i++) {
            List<Todo> todos = todoService.getTodoByDefaultGroup(Integer.parseInt(userId),month);
            //数据结构扩充接口
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("month",month);
            data.put("todos",todos);
            data.put("default",1);
            items.add(data);
        }
        Map<String,Object> resutl=new HashMap<String,Object>();
        resutl.put("items",items);
        resutl.put("itemnumber",items.size());
        return result(resutl);
    }
}
