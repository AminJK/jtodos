package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserByName(String username);
}
