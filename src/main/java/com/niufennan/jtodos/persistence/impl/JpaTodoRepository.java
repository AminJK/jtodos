package com.niufennan.jtodos.persistence.impl;

import com.niufennan.jtodos.models.Todo;
import com.niufennan.jtodos.persistence.TodoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.*;
import java.util.List;

@Transactional
@Repository
public class JpaTodoRepository implements TodoRepository {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    public List<Todo> getAll() {
        CriteriaQuery<Todo> criteriaQuery=entityManagerFactory.createEntityManager().getCriteriaBuilder().createQuery(Todo.class);
        return entityManagerFactory.createEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<Todo> getTodoByUserId(int userId) {
        CriteriaBuilder builder=entityManagerFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery=builder.createQuery(Todo.class);
        Root<Todo> todoRoot = criteriaQuery.from(Todo.class);
        Predicate predicate = builder.equal(todoRoot.get("userId"), userId);
        criteriaQuery.where(predicate);
        return entityManagerFactory.createEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public void save(Todo todo) {
        entityManagerFactory.createEntityManager().persist(todo);
    }
}
