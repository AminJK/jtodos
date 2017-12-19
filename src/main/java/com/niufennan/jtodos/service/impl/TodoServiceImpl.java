package com.niufennan.jtodos.service.impl;

import com.niufennan.jtodos.dao.TodoDao;
import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.persistence.TodoRepository;
import com.niufennan.jtodos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    //private TodoDao todoDao;
    public void save(Todo todo) {
        todoRepository.save(todo);
        //todoDao.save(todo);
    }

    public List<Todo> getTodoByUserId(int userId) {
        return todoRepository.getTodoByUserId(userId);
        //return todoDao.getTodoByUserId(userId);
    }
}
