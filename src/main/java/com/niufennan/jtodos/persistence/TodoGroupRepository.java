package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.models.TodoGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoGroupRepository  extends JpaRepository<TodoGroup,Integer> {
    public TodoGroup findByIsDefaultAndUserId(Integer isDefault,Integer userId);
}
