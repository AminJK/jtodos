package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.TodoGroup;
import com.niufennan.jtodos.persistence.TodoGroupRepository;
import com.niufennan.jtodos.persistence.TodoRepository;
import com.niufennan.jtodos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        System.out.println(todoGroup.getId());
        DateBetween between=getDate(month);
        System.out.println(between.getFirstDate());
        System.out.println(between.getEndDate());

        List<Todo>  todos= todoRepository.getByGroupIdAndCreateTimeBetween(todoGroup.getId(),between.getFirstDate(),between.getEndDate());
        return todos;
    }

    private DateBetween getDate( int month ){

        DateBetween between=new DateBetween();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstCalender =  Calendar.getInstance();
        // 获取前月的第一天
        firstCalender.set(Calendar.MONTH,month);
        firstCalender.add(Calendar.MONTH, 0);
        firstCalender.set(Calendar.DAY_OF_MONTH, 1);
        firstCalender.set(Calendar.HOUR_OF_DAY,0);
        firstCalender.set(Calendar.MINUTE,0);
        firstCalender.set(Calendar.SECOND,0);
        between.setFirstDate(firstCalender.getTime());
        // 获取前月的最后一天
        Calendar endCalender =   Calendar.getInstance();
        endCalender.set(Calendar.MONTH,++month);
        endCalender.set(Calendar.DAY_OF_MONTH, 1);
        endCalender.set(Calendar.HOUR_OF_DAY,0);
        endCalender.set(Calendar.MINUTE,0);
        endCalender.set(Calendar.SECOND,0);
        endCalender.add(Calendar.SECOND,-1);
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
