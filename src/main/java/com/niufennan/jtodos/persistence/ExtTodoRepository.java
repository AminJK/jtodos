package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Todo;

import java.util.List;

public interface ExtTodoRepository {
    public List<Todo> getTodoByUserId(int userId);
}
