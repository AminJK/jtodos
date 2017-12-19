package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> ,ExtTodoRepository{

}
