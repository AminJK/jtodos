package com.niufennan.jtodos.persistence.impl;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.persistence.ExtTodoRepository;
import com.niufennan.jtodos.persistence.TodoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class TodoRepositoryImpl implements ExtTodoRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Todo> getTodoByUserId(int userId) {
        String sql="select t from  com.niufennan.jtodos.models.Todo t where  t.userId=:userId";
        Query query= entityManager.createQuery(sql);
        query.setParameter("userId",userId);
        return query.getResultList();
    }
}
