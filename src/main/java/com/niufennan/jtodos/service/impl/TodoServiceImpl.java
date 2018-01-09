package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.TodoGroup;
import com.niufennan.jtodos.persistence.TodoGroupRepository;
import com.niufennan.jtodos.persistence.TodoRepository;
import com.niufennan.jtodos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoGroupRepository todoGroupRepository;
    public void save(Todo todo) {
        todoRepository.save(todo);
        //todoDao.save(todo);
    }

    public List<Todo> getTodoByUserId(int userId) {
        return todoRepository.getByUserId(userId);
        //return todoDao.getTodoByUserId(userId);
    }

    public List<Todo> getTodoByDefaultGroup(int userId,int month) {
        TodoGroup todoGroup=todoGroupRepository.findByIsDefaultAndUserId(1,userId);
        DateBetween between=getDate(month);
        List<Todo>  todos= todoRepository.getByGroupIdAndCreateTimeBetween(todoGroup.getId(),between.getFirstDate(),between.getEndDate());
        return todos;
    }

    private DateBetween getDate( int month ){

        DateBetween between=new DateBetween();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstCalender =  Calendar.getInstance();
        // 获取前月的第一天
        firstCalender = Calendar.getInstance();
        firstCalender.add(Calendar.MONTH, 0);
        firstCalender.set(Calendar.DAY_OF_MONTH, 1);
        between.setFirstDate(firstCalender.getTime());
        // 获取前月的最后一天
        Calendar endCalender =   Calendar.getInstance();
        endCalender.add(Calendar.MONTH, 1);
        endCalender.set(Calendar.DAY_OF_MONTH, 0);
        between.setEndDate(endCalender.getTime());
        return  between;
    }
}
class DateBetween{
    private Date firstDate;
    private Date endDate;

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
