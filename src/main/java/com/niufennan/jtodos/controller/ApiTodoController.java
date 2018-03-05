package com.niufennan.jtodos.controller;

import com.niufennan.jtodos.base.BaseController;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.models.UserMessage;
import com.niufennan.jtodos.service.TodoService;
import com.niufennan.jtodos.service.UserService;
import com.niufennan.jtodos.service.WeatherService;
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

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/api/index",method = RequestMethod.POST)
    public Object apiIndex(HttpServletRequest request,@RequestBody Map map){
        //获取首页数据
        String userId=request.getAttribute("tokenId").toString();
        Integer month=Integer.parseInt( map.get("month").toString());
        Integer year=Integer.parseInt(map.get("year").toString());
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        while(true){
            List<Todo> todos = todoService.getTodoByDefaultGroup(Integer.parseInt(userId),year,month);
            //数据结构扩充接口
            if(todos.size()<=0){
                month--;
                if(month<0){
                    break;
                }
            }else {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("month", month+1); //java的月份从0开始
                data.put("year", year);
                data.put("todos", todos);
                data.put("default", 1);
                items.add(data);
                break;
            }
        }
        Map<String,Object> resutl=new HashMap<String,Object>();
        resutl.put("items",items);
        resutl.put("itemnumber",items.size());
        return result(resutl);
    }

    @RequestMapping(value = "/api/indexData",method = RequestMethod.POST)
    public Object apiIndexData(HttpServletRequest request,@RequestBody Map map){
        //获取首页数据
        String userId=request.getAttribute("tokenId").toString();
        Integer month=Integer.parseInt( map.get("month").toString());
        Integer year=Integer.parseInt(map.get("year").toString());
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        List<Todo> todos = todoService.getTodoByDefaultGroup(Integer.parseInt(userId),year,month);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("month", month+1); //java的月份从0开始
        data.put("todos", todos);
        data.put("year", year);
        data.put("default", 1);
        items.add(data);

        Map<String,Object> resutl=new HashMap<String,Object>();
        resutl.put("items",items);
        resutl.put("itemnumber",items.size());
        return result(resutl);
    }

    @RequestMapping(value = "/api/calendarTodoList",method = RequestMethod.POST)
    public Object calendarTodoList(HttpServletRequest request,@RequestBody Map map){
        Integer userId= Integer.parseInt(request.getAttribute("tokenId").toString());
        Integer year=Integer.parseInt( map.get("year").toString());
        Integer month=Integer.parseInt( map.get("month").toString());
        Integer day=Integer.parseInt(map.get("day").toString());
        Integer groupId=Integer.parseInt(map.get("groupId").toString());
        List<Todo> todos = todoService.getTodoByCalendarTodoList(userId,groupId,year,month,day);
        return result(todos);
    }

    @RequestMapping(value = "/api/saveTodos",method = RequestMethod.POST)
    public Object saveTodos(HttpServletRequest request,@RequestBody Map map){
        //获取用户
        String userId=request.getAttribute("tokenId").toString();
        //创建对象
        Todo todo=new Todo();
        todo.setCreateTime(new Date());
        todo.setUserId(Integer.parseInt(userId));
        todo.setAddress(map.get("address").toString());
        todo.setBookmark(Integer.parseInt(map.get("bookmark").toString()));
        todo.setItem(map.get("item").toString());
        todo.setContent(map.get("conent").toString());
        todo.setGroupId(Integer.parseInt(map.get("groupId").toString()));
        todo.setLat(Double.parseDouble(map.get("lat").toString()));
        todo.setLng(Double.parseDouble(map.get("lng").toString()));
        todo.setMood(Integer.parseInt(map.get("mood").toString()));
        todo.setWeather(Integer.parseInt(map.get("weather").toString()));
        todo.setWeatherContent(map.get("weatherContent").toString());
        todoService.save(todo);
        return result();
    }
    @RequestMapping(value = "/api/weather",method = RequestMethod.POST)
    public Object getWeather(HttpServletRequest request,@RequestBody Map map){
        return result(weatherService.weather(map.get("address").toString()));
    }
}
