package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository {
    public List<Todo> getAll();
    public List<Todo> getTodoByUserId(int userId);
    public void save(Todo todo);
}
