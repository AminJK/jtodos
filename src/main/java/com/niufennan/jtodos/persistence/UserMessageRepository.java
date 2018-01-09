package com.niufennan.jtodos.persistence;

import com.niufennan.jtodos.models.User;
import com.niufennan.jtodos.models.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage,Integer> {
}
