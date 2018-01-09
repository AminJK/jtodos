package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    public List<Todo> getByUserId(int userId);
    public List<Todo> getByGroupId(int groupId);
    public List<Todo> getByGroupIdAndCreateTimeBetween(int groupId, Date startDate,Date endDate);

}
