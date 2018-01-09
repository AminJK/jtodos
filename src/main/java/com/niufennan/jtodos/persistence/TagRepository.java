package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.Tag;
import com.niufennan.jtodos.models.TodoGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer> {
}
